/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import ru.agilecamp.habitator.HabitsException;
import ru.agilecamp.habitator.UserService;

/**
 *
 * @author d_alex
 */
public class UserServiceTest {

    @Test
    public void shouldCreateUserWhenRegistered() throws SQLException, HabitsException {
        DataSourceMockBuilder dataSourceMockBuilder = new DataSourceMockBuilder()
                .whenResultSetNextReturn(false);

        UserService userService = new UserService(dataSourceMockBuilder.getDataSourceMock());

        userService.registerUser("testLogin", "testPassword", "testPassword");
        verify(dataSourceMockBuilder.getPreparedStatement()).execute();
    }

    @Test
    public void shouldDropUser() throws SQLException, HabitsException {
        DataSourceMockBuilder dataSourceMockBuilder = new DataSourceMockBuilder();

        UserService userService = new UserService(dataSourceMockBuilder.getDataSourceMock());

        userService.dropUser(1);
        verify(dataSourceMockBuilder.getPreparedStatement()).execute();
    }

    @Test
    public void shouldLoginUser() throws SQLException {
        DataSourceMockBuilder dataSourceMockBuilder = new DataSourceMockBuilder()
                .whenResultSetNextReturn(true);
        when(dataSourceMockBuilder.getResultSetMock().getInt("id")).thenReturn(1);

        UserService userService = new UserService(dataSourceMockBuilder.getDataSourceMock());
        Integer userId = userService.authentication("testLogin", "testPassword");

        assertNotNull(userId);
    }
}
