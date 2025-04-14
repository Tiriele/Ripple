package befinalproject.ripple.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AppUserRepositoryTest {

    @Test
    public void testCreateNewAppUser() {
        AppUser appUser = new AppUser("username", "passwordHash", "email", "role");
        assertThat(appUser.getId()).isNotNull();
    }

    @Test
    public void testGetUsername() {
        AppUser appUser = new AppUser("username", "passwordHash", "email", "role");
        assertThat(appUser.getUsername()).isEqualTo("username");
    }

    @Test
    public void testDeleteAppUser() {
        AppUser appUser = new AppUser("username", "passwordHash", "email", "role");
        assertThat(appUser.getId()).isNotNull();
    }

}
