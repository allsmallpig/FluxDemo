package import_excel;

/**
 * @author ltz
 * @version V1.0
 * @date 2020/11/2 15:57
 * @Description
 * @email goodmanalibaba@foxmail.com
 */
import java.util.Arrays;

public class BigExcelReaderTest {
    public static void main(String[] args) throws Exception{
        String filepath = "D:\\workspace\\OA办公系统.xlsx";
        BigExcelReader reader = new BigExcelReader(filepath) {
            @Override
            protected void outputRow(String[] datas, int[] rowTypes, int rowIndex) {
                // 此处输出每一行的数据
                System.out.println(Arrays.toString(datas));
            }
        };
        // 执行解析
        reader.parse();
    }
}