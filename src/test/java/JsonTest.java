import author.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Tag("FILE_TEST")
    @DisplayName("Проверка json файла")
    void jsonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("author.json");
             Reader reader = new InputStreamReader(is)) {
            Author author = objectMapper.readValue(reader, Author.class);

            assertThat(author.getName()).isEqualTo("Theodore Dreiser");
            assertThat(author.getId()).isEqualTo(24236);
            assertThat(author.getAveragePriceBook()).isEqualTo(114.36);
            assertThat(author.getTotalQuantity()).isEqualTo(51);
            assertThat(author.getBooks()).isNotNull();
            assertThat(author.getBooks().size()).isEqualTo(3);


       /* JUnit's  asserts
            assertEquals("Theodore Dreiser", author.getName());
            assertEquals(24236, author.getId());
            assertEquals(114.36, author.getAveragePriceBook());
            assertEquals(51, author.getTotalQuantity());
            assertNotNull(author.getBooks());
            assertEquals(3, author.getBooks().size());
            assertThat(author.getBooks()).containsAll(List.of("The Financier", "Sister Carrie", "The Titan")));

 */
        }
    }
}
