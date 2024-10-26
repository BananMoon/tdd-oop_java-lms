package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("게시글을 삭제한다.")
    @Test
    void delete_success() throws Exception {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertTrue(Q1.isDeleted());
    }

    @DisplayName("로그인한 사용자와 질문자가 다르면 질문글을 삭제할 수 없다.")
    @Test
    void delete_fail_loginUser_is_not_question_writer() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Question 객체로 DeleteHistory 객체를 생성한다.")
    @Test
    void create_DeleteHistory() {
        NsUser writer = new NsUser(1L, "moon", "1234", "moonyoonji", "moon@a.com");
        Question question = new Question(writer, "title", "question contents");

        List<DeleteHistory> actual = question.toDeleteHistory();

        assertThat(actual)
                .extracting("deletedBy", "contentType")
                .containsExactly(tuple(writer, ContentType.QUESTION));
    }
}
