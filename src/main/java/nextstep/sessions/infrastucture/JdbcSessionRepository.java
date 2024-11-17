package nextstep.sessions.infrastucture;

import nextstep.sessions.domain.FreeSession;
import nextstep.sessions.domain.PaidSession;
import nextstep.sessions.domain.SessionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    @Override
    public Optional<FreeSession> findFreeSessionById(Long sessionId) {
        return Optional.empty();
    }

    @Override
    public Optional<PaidSession> findPaidSessionById(Long sessionId) {
        return Optional.empty();
    }
}
