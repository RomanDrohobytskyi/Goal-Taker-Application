package application.unit;

import application.utils.date.DateParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DateParserTest {

    @Test
    void parsedDatePresentAndCorrect(){
        String expectedDate = "Thu Feb 20 00:00:00 CET 2020";
        String date = "2020-02-20";
        Optional<Date> parsedDate = DateParser.parseStringToDateForDefaultPattern(date);

        assertThat(parsedDate.isPresent());
        assertThat(parsedDate.get().toString()).isEqualTo(expectedDate);
    }

    @Test
    void parseWrongDate(){
        String unparseableDate = "231";
        Optional<Date> shouldBeEmptyParsedDate = DateParser.parseStringToDateForDefaultPattern(unparseableDate);
        Optional<Date> expectedEmptyOptionalDate = Optional.empty();
        assertThat(shouldBeEmptyParsedDate).isEqualTo(expectedEmptyOptionalDate);
    }

}
