package aafanasyevaa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Tests extends TestData {

    @ValueSource(strings = {"Ариэль", "Моана"})
    @ParameterizedTest(name = "Поиск принцессы {0} на сайте Disney")
    public void findDisneyPrincess(String searchQuery) {
        open(url);
        $("#search").setValue(searchQuery).pressEnter();
        $("#search-container").shouldHave(Condition.text(searchQuery));
    }

    @CsvSource(value = {
            "Ариэль,Начало истории",
            "Моана, Атака пиратов"
    })
    @ParameterizedTest(name = "Поиск принцессы {0} на сайте Disney и проверка отображения текста {1}")
    public void findDisneyPrincessAndCheckResult(String searchQuery, String result) {
        open(url);
        $("#search").setValue(searchQuery).pressEnter();
        $("#search-container").shouldHave(Condition.text(result));
    }

    @EnumSource(Princess.class)
    @ParameterizedTest(name = "Поиск принцессы {0} на сайте Disney и проверка отображения текста {1}")
    public void findDisneyPrincessAndCheckResult(Princess princess) {
        open(url);
        $("#search").setValue(princess.name()).pressEnter();
        $("#search-container").shouldHave(Condition.text(princess.name()));
    }

    static Stream<Arguments> findDisneyPrincessAndCheckResults() {
        return Stream.of(
                Arguments.of("Ариэль", "Начало истории"),
                Arguments.of("Моана", "Атака пиратов")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Поиск принцессы {0} на сайте Disney и проверка отображения текста {1}")
    public void findDisneyPrincessAndCheckResults(String searchQuery, String result) {
        open(url);
        $("#search").setValue(searchQuery).pressEnter();
        $("#search-container").shouldHave(Condition.text(String.valueOf(result)));
    }
}
