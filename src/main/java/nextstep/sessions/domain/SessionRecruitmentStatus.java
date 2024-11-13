package nextstep.sessions.domain;

import nextstep.sessions.CannotRegisterException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum SessionRecruitmentStatus {
    COSED(FALSE),
    OPEN(TRUE);

    private final Boolean registerAvailable;

    SessionRecruitmentStatus(Boolean registerAvailable) {
        this.registerAvailable = registerAvailable;
    }

    public void checkRegisterAvailable() {
        if (FALSE.equals(this.registerAvailable)) {
            throw new CannotRegisterException("강의 모집 상태가 '모집 중'이 아닐 때 강의 수강 신청이 불가합니다.");
        }
    }
}
