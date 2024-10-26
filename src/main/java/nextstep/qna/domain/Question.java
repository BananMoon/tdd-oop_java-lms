package nextstep.qna.domain;

import nextstep.global.domain.BaseEntity;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseEntity {
    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

    private boolean deleted = false;


    public Question() {
        super();
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        super(id);
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        this.validateWriter(loginUser);

        if (this.hasAnswers()) {
            this.answers.delete(loginUser);
        }

        this.deleted = true;
    }

    private void validateWriter(NsUser loginUser) throws CannotDeleteException {
        if (loginUser.isSameUser(this.writer)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean hasAnswers() {
        return !this.answers.isEmpty();
    }

    public List<DeleteHistory> toDeleteHistory() {
        List<DeleteHistory> totalDeleteHistory = new ArrayList<>();
        totalDeleteHistory.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));
        totalDeleteHistory.addAll(answers.toDeleteHistories());
        return totalDeleteHistory;
    }
}
