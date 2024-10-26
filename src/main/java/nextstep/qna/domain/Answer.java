package nextstep.qna.domain;

import nextstep.global.domain.BaseEntity;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseEntity {
    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    public Answer() {
        super();
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public NsUser getWriter() {
        return writer;
    }


    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        this.validateAnswer(loginUser);
        this.deleted = true;
    }

    private void validateAnswer(NsUser loginUser) throws CannotDeleteException {
        if (loginUser.isSameUser(this.writer)) {
            throw new CannotDeleteException("답변을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteHistory toDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, this.id, this.writer, LocalDateTime.now());
    }
}
