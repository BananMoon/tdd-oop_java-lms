package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.as;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

class AnswersTest {

    @DisplayName("여러 답글들을 Answers 객체로 생성한다.")
    @Test
    void create() {
        Answer answer1 = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "answer1");
        Answer answer2 = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "answer2");

        Answers actual = new Answers(List.of(answer1, answer2));

        assertThat(actual).extracting("answers", as(LIST))
                .containsExactly(answer1, answer2);
    }
}
