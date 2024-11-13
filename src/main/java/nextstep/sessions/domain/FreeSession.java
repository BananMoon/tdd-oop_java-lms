package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsStudent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session{
    public FreeSession(Long id, Course course, List<NsStudent> students, String title, List<Image> coverImages,
                       SessionProgressStatus sessionStatus2, SessionRecruitmentStatus sessionRecruitmentStatus,
                       LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, course, students, title, coverImages, SessionFeeStatus.FREE, 0, sessionStatus2, sessionRecruitmentStatus,
                startDate, endDate, createdAt, updatedAt);
    }
}
