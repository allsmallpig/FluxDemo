package import_excel;

/**
 * @author ltz
 * @version V1.0
 * @date 2020/11/2 15:56
 * @Description 参考 https://blog.csdn.net/zp19890228/article/details/41822617
 * @email goodmanalibaba@foxmail.com
 */

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class BigExcelReader {

    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    public static final int ERROR = 1;
    public static final int BOOLEAN = 1;
    public static final int NUMBER = 2;
    public static final int STRING = 3;
    public static final int DATE = 4;
    public static final String DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";


    //	private DataFormatter formatter = new DataFormatter();、
    private List<InputStream> sheetList = Lists.newArrayList();
    private List<XMLReader> parserList = Lists.newArrayList();
    private List<InputSource> sheetSourceList = Lists.newArrayList();
    //    private InputStream sheet;
//    private XMLReader parser;
//    private InputSource sheetSource;
    private int index = 0;

    /**
     * 读大数据量Excel
     *
     * @param sheetNaturalSerialNumber 读取一个excel文件中的第几个表格  null/默认全部
     * @param filename  文件名
     * @param maxColNum 读取的最大列数
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    public BigExcelReader(String filename, Integer sheetNaturalSerialNumber) throws IOException, OpenXML4JException, SAXException {
        OPCPackage pkg = OPCPackage.open(filename);
        init(pkg,sheetNaturalSerialNumber);
    }

    /**
     * 读大数据量Excel
     *
     * @param sheetNaturalSerialNumber 读取一个excel文件中的第几个表格  null/默认全部
     * @param file      Excel文件
     * @param maxColNum 读取的最大列数
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    public BigExcelReader(File file, Integer sheetNaturalSerialNumber) throws IOException, OpenXML4JException, SAXException {
        OPCPackage pkg = OPCPackage.open(file);
        init(pkg,sheetNaturalSerialNumber);
    }

    /**
     * 读大数据量Excel
     *
     * @param sheetNaturalSerialNumber 读取一个excel文件中的第几个表格  null/默认全部
     * @param in Excel文件输入流
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    public BigExcelReader(InputStream in, Integer sheetNaturalSerialNumber) throws IOException, OpenXML4JException, SAXException {
        OPCPackage pkg = OPCPackage.open(in);
        init(pkg,sheetNaturalSerialNumber);
    }

    /**
     * 初始化 将Excel转换为XML
     *
     * @param pkg
     * @param sheetNaturalSerialNumber 读取一个excel文件中的第几个表格  null/默认全部
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    private void init(OPCPackage pkg, Integer sheetNaturalSerialNumber) throws IOException, OpenXML4JException, SAXException {
        XSSFReader xssfReader = new XSSFReader(pkg);
        SharedStringsTable sharedStringsTable = xssfReader.getSharedStringsTable();
        StylesTable stylesTable = xssfReader.getStylesTable();
        Iterator<InputStream> sheets = xssfReader.getSheetsData();

        if (sheets instanceof XSSFReader.SheetIterator) {
            XSSFReader.SheetIterator sheetiterator = (XSSFReader.SheetIterator) sheets;
            int i = 0;
            while (sheetiterator.hasNext()) {
                i++;
                InputStream dummy = sheetiterator.next();
                if (null != sheetNaturalSerialNumber && i == sheetNaturalSerialNumber.intValue()) {
                    addSheetsParams(xssfReader, sharedStringsTable, stylesTable, sheetiterator, i, dummy);
                    break;
                } else if (null == sheetNaturalSerialNumber) {
                    addSheetsParams(xssfReader, sharedStringsTable, stylesTable, sheetiterator, i, dummy);
                } else if (null != sheetNaturalSerialNumber && i != sheetNaturalSerialNumber.intValue()) {
                    continue;
                }
            }
        }

    }

    private void addSheetsParams(XSSFReader xssfReader, SharedStringsTable sharedStringsTable, StylesTable stylesTable, XSSFReader.SheetIterator sheetiterator, int i, InputStream dummy) throws IOException, InvalidFormatException, SAXException {
        System.out.println(sheetiterator.getSheetName());
        InputStream sheet = xssfReader.getSheet("rId" + i);
        sheetList.add(sheet);
        XMLReader parser = fetchSheetParser(sharedStringsTable, stylesTable);
        parserList.add(parser);
        InputSource sheetSource = new InputSource(sheet);
        sheetSourceList.add(sheetSource);
        dummy.close();
    }

    /**
     * 执行解析操作
     *
     * @return 读取的Excel行数
     */
    public int parse() {
        int size = parserList.size();
        for (int i = 0; i < size; i++) {
            XMLReader parser = parserList.get(i);
            InputStream sheet = sheetList.get(i);
            try {
                InputSource sheetSource = sheetSourceList.get(i);
                parser.parse(sheetSource);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } finally {
                if (sheet != null) {
                    try {
                        sheet.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return index;
    }

    private XMLReader fetchSheetParser(SharedStringsTable sharedStringsTable, StylesTable stylesTable) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser"
                );
        ContentHandler handler = new SheetHandler(sharedStringsTable, stylesTable);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * SAX解析的处理类
     * 每解析一行数据后通过outputRow(String[] datas, int[] rowTypes, int rowIndex)方法进行输出
     *
     * @author zpin
     */
    private class SheetHandler extends DefaultHandler {
        private SharedStringsTable sharedStringsTable; // 存放映射字符串
        private StylesTable stylesTable;// 存放单元格样式
        private String readValue;// 存放读取值
        private xssfDataType dataType;// 单元格类型
        private String[] rowDatas;// 存放一行中的所有数据
        private int[] rowTypes;// 存放一行中所有数据类型
        private int colIdx;// 当前所在列

        private short formatIndex;
//		private String formatString;// 对数值型的数据直接读为数值，不对其格式化，所以隐掉此处

        private SheetHandler(SharedStringsTable sst, StylesTable stylesTable) {
            this.sharedStringsTable = sst;
            this.stylesTable = stylesTable;
        }

        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {
            if (name.equals("c")) {// c > 单元格
                colIdx = getColumn(attributes);
                String cellType = attributes.getValue("t");
                String cellStyle = attributes.getValue("s");

                this.dataType = xssfDataType.NUMBER;
                if ("b".equals(cellType)) {
                    this.dataType = xssfDataType.BOOL;
                } else if ("e".equals(cellType)) {
                    this.dataType = xssfDataType.ERROR;
                } else if ("inlineStr".equals(cellType)) {
                    this.dataType = xssfDataType.INLINESTR;
                } else if ("s".equals(cellType)) {
                    this.dataType = xssfDataType.SSTINDEX;
                } else if ("str".equals(cellType)) {
                    this.dataType = xssfDataType.FORMULA;
                } else if (cellStyle != null) {
                    int styleIndex = Integer.parseInt(cellStyle);
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex = style.getDataFormat();
//		            this.formatString = style.getDataFormatString();
                }
            }
            // 解析到一行的开始处时，初始化数组
            else if (name.equals("row")) {
                int cols = getColsNum(attributes);// 获取该行的单元格数
                rowDatas = new String[cols];
                rowTypes = new int[cols];
            }
            readValue = "";
        }

        public void endElement(String uri, String localName, String name)
                throws SAXException {
            if (name.equals("v")) { // 单元格的值
                switch (this.dataType) {
                    case BOOL: {
                        char first = readValue.charAt(0);
                        rowDatas[colIdx] = first == '0' ? "FALSE" : "TRUE";
                        rowTypes[colIdx] = BOOLEAN;
                        break;
                    }
                    case ERROR: {
                        rowDatas[colIdx] = "ERROR:" + readValue.toString();
                        rowTypes[colIdx] = ERROR;
                        break;
                    }
                    case INLINESTR: {
                        rowDatas[colIdx] = new XSSFRichTextString(readValue).toString();
                        rowTypes[colIdx] = STRING;
                        break;
                    }
                    case SSTINDEX: {
                        int idx = Integer.parseInt(readValue);
                        rowDatas[colIdx] = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx)).toString();
                        rowTypes[colIdx] = STRING;
                        break;
                    }
                    case FORMULA: {
                        rowDatas[colIdx] = readValue;
                        rowTypes[colIdx] = STRING;
                        break;
                    }
                    case NUMBER: {
                        // 判断是否是日期格式
                        if (HSSFDateUtil.isADateFormat(formatIndex, readValue)) {
                            Double d = Double.parseDouble(readValue);
                            Date date = HSSFDateUtil.getJavaDate(d);
                            rowDatas[colIdx] = DateFormatUtils.format(date, DATE_FORMAT_STR);
                            rowTypes[colIdx] = DATE;
                        }
//			            else if (formatString != null){
//			            	cellData.value = formatter.formatRawCellContents(Double.parseDouble(cellValue), formatIndex, formatString);
//			            	cellData.dataType = NUMBER;
//			            }
                        else {
                            rowDatas[colIdx] = readValue;
                            rowTypes[colIdx] = NUMBER;
                        }
                        break;
                    }
                }
            }

            if (HSSFDateUtil.isADateFormat(formatIndex, readValue)) {
                Double d = Double.parseDouble(readValue);
                Date date = HSSFDateUtil.getJavaDate(d);
                String cdate = DateFormatUtils.format(date, DATE_FORMAT_STR);
                if (cdate.substring(11).equals("00:00:00")) {
                    cdate = cdate.substring(0, 9);
                }
                rowDatas[colIdx] = cdate;
                rowTypes[colIdx] = DATE;
                formatIndex = 0;
            }
            // 当解析的一行的末尾时，输出数组中的数据
            else if (name.equals("row")) {
                outputRow(rowDatas, rowTypes, index++);
            }
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            readValue += new String(ch, start, length);
        }
    }

    /**
     * 输出每一行的数据
     *
     * @param datas    数据
     * @param rowTypes 数据类型
     * @param rowIndex 所在行
     */
    protected abstract void outputRow(String[] datas, int[] rowTypes, int rowIndex);

    private int getColumn(Attributes attrubuts) {
        String name = attrubuts.getValue("r");
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            if (Character.isDigit(name.charAt(i))) {
                break;
            }
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    private int getColsNum(Attributes attrubuts) {
        String spans = attrubuts.getValue("spans");
        String cols = spans.substring(spans.indexOf(":") + 1);
        return Integer.parseInt(cols);
    }
}