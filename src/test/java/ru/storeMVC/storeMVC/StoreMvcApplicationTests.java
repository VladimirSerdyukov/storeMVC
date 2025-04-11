package ru.storeMVC.storeMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.storeMVC.storeMVC.dto.UserDto;

import java.util.UUID;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StoreMvcApplicationTests {

	@Autowired
	private ApplicationContext StoreMvcApplication;

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {

	}

	@Test
	public void testSummaryUser() throws Exception {
		UUID id = UUID.fromString("01962503-b319-767a-b027-062fbcc6e68d");
		UserDto user = new UserDto(id, "Petya", "petya@mail.com");
		mockMvc.perform(get("/user/summary/01962503-b319-767a-b027-062fbcc6e68d")
						.contentType(MediaType.TEXT_PLAIN))
				.andDo(System.out::println)
				.andExpect(status().isOk())
				.andExpect(jsonPath("uuid").value(id.toString()))
				.andExpect(jsonPath("name").value("Petya"))
				.andExpect(jsonPath("email").value("petya@mail.com"));
	}

	@Test
	public void testDetailsUser() throws Exception {
		UUID id = UUID.fromString("01962503-b319-767a-b027-062fbcc6e68d");
		UserDto user = new UserDto(id, "Petya", "petya@mail.com");
		mockMvc.perform(get("/user/details/01962503-b319-767a-b027-062fbcc6e68d")
						.contentType(MediaType.TEXT_PLAIN))
				.andDo(System.out::println)
				.andExpect(status().isOk())
				.andExpect(jsonPath("uuid").value(id.toString()))
				.andExpect(jsonPath("name").value("Petya"))
				.andExpect(jsonPath("email").value("petya@mail.com"))
				.andExpect(jsonPath("orders").exists());
	}

	@Test
	public void testAllOrders() throws Exception{
		mockMvc.perform(get("/store/order/all?page=0&size=3&sort=id,asc")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].length()").value(3));

	}
}
