package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("로그인한 사용자와 답변의 작성자가 같으면 답변을 삭제할 수 있다.")
    @Test
    void delete_writer_is_same_as_loginUser() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertTrue(A1.isDeleted());
    }

    @DisplayName("로그인한 사용자와 답변의 작성자가 다르면 답변을 삭제할 수 없다.")
    @Test
    void delete_fail_writer_is_not_same_as_loginUser() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Answer 객체로 DeleteHistory 객체를 생성한다.")
    @Test
    void create_DeleteHistory() {
        NsUser writer = new NsUser(1L, "moon", "1234", "moonyoonji", "moon@a.com");
        Answer answer = new Answer(writer, new Question(writer, "title", "question contents"), "answer contents");
        DeleteHistory actual = answer.toDeleteHistory();

        assertThat(actual)
                .extracting("deletedBy", "contentType")
                .contains(writer, ContentType.ANSWER);
    }
}
