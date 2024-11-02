package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsStudent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session{
    public FreeSession(Course course, List<NsStudent> students, Long id, String title, Image coverImage, SessionStatus sessionStatus,
                       LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(course, students, id, title, coverImage, SessionFeeStatus.PAID, sessionStatus, startDate, endDate, createdAt, updatedAt);
    }
}
