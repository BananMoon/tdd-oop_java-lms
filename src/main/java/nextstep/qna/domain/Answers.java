package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isEmpty() {
        return Objects.isNull(this.answers) || this.answers.isEmpty();
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> totalDeleteHistory = new ArrayList<>();

        for (Answer answer : this.answers) {
            totalDeleteHistory.add(answer.toDeleteHistory());
        }
        return totalDeleteHistory;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
