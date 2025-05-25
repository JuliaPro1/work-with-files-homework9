import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class FilesParsingTest {
    private ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    @Tag("FILE_TEST")
    @DisplayName("Проверка pdf файла из zip архива")
    void pdfTest() throws Exception {
        boolean pdfFound = false;
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("differentFiles.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    pdfFound = true;
                    PDF pdf = new PDF(zis);

                    assertThat(pdf.creator).contains("Writer");
                    assertThat(pdf.numberOfPages).isEqualTo(4);
                    /* JUnit's  asserts
                    Assertions.assertEquals("Writer", pdf.creator);
                    Assertions.assertEquals(4, pdf.numberOfPages);
                    */
                }
            }
            assertThat(pdfFound).isTrue();
        }

    }

    @Test
    @Tag("FILE_TEST")
    @DisplayName("Проверка xls файла из zip архива")
    void xlsTest() throws Exception {
        boolean xlsxFound = false;
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("differentFiles.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    xlsxFound = true;
                    XLS xls = new XLS(zis);
                    String actualValueString = xls.excel
                            .getSheetAt(0)
                            .getRow(1)
                            .getCell(4)
                            .getStringCellValue();
                    double actualValueDouble = xls.excel
                            .getSheetAt(0)
                            .getRow(7)
                            .getCell(6)
                            .getNumericCellValue();

                    assertThat(actualValueString).contains("Regular Air");
                    assertThat(actualValueDouble).isEqualTo(9.11);
                    /*JUnit's  asserts
                    Assertions.assertTrue(actualValueString.contains("Regular Air"));
                    Assertions.assertEquals(9.11, actualValueDouble);
                     */
                }
            }
            assertThat(xlsxFound).isTrue();
        }
    }

    @Test
    @Tag("FILE_TEST")
    @DisplayName("Проверка scv файла из zip архива")
    void scvTest() throws Exception {
        boolean scvFound = false;
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("differentFiles.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    scvFound = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    org.assertj.core.api.Assertions.assertThat(content).contains(
                            new String[]{"Name", "Team", "Position"},
                            new String[]{"Adam Donachie", "BAL", "Catcher"},
                            new String[]{"Kevin Millar", "BAL", "First Baseman"},
                            new String[]{"Chris Gomez", "BAL", "First Baseman"}
                    );
                }
            }
            assertThat(scvFound).isTrue();
        }
    }
}
