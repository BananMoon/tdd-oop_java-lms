package nextstep.sessions.domain;

import nextstep.sessions.CannotRegisterException;

import java.util.Arrays;
import java.util.List;

public enum SessionProgressStatus {
    PREPARING(List.of("PREPARING"), Boolean.FALSE),
    ONGOING(List.of("RECRUITING", "ONGOING"), Boolean.TRUE),
    ENDED(List.of("ENDED"), Boolean.FALSE);

    private final List<String> dbDatas;
    private final Boolean registerAvailable;

    SessionProgressStatus(List<String> dbDatas, Boolean registerAvailable) {
        this.dbDatas = dbDatas;
        this.registerAvailable = registerAvailable;
    }

    public void checkRegisterAvailable() {
        if (Boolean.FALSE.equals(this.registerAvailable)) {
            throw new CannotRegisterException("강의 진행 상태가 '진행 중'이 아닐 때 강의 수강 신청이 불가합니다.");
        }
    }

    public static SessionProgressStatus of(String dbData) {
        return Arrays.stream(values())
                .filter(value -> value.dbDatas.contains(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("'%s'에 해당하는 SessionProgressStatus 상수가 없습니다.", dbData)));
    }

}
