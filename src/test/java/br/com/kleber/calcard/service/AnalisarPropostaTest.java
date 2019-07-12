
package br.com.kleber.calcard.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnalisarPropostaTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void aprovadoEntre100e500() throws Exception {
		
		final StringBuilder content = new StringBuilder("{")
				.append("\"nome\": \"Ana\",")
				.append("\"cpf\": \"04609998911\",")
				.append("\"idade\": 17,")
				.append("\"sexo\": \"f\",")
				.append("\"estadoCivil\": \"solteiro\",")
				.append("\"estado\": \"sp\",")
				.append("\"dependentes\": \"0\",")
				.append("\"renda\": \"1000\"")
				.append("}");
		mockMvc.perform(MockMvcRequestBuilders.post("/cliente/analisar")
				.content(content.toString())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value("Aprovado"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.limite").value("entre-100-500")).andReturn();
	}

	@Test
	public void aprovadoEntre500e1000() throws Exception {
		final StringBuilder content = new StringBuilder("{")
				.append("\"nome\": \"Lucas\",")
				.append("\"cpf\": \"04619998911\",")
				.append("\"idade\": 28,")
				.append("\"sexo\": \"m\",")
				.append("\"estadoCivil\": \"solteiro\",")
				.append("\"estado\": \"sc\",")
				.append("\"dependentes\": \"0\",")
				.append("\"renda\": \"2500\"")
				.append("}");
		mockMvc.perform(MockMvcRequestBuilders.post("/cliente/analisar")
				.content(content.toString())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value("Aprovado"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.limite").value("entre-500-1000")).andReturn();
	}

	@Test
	public void aprovadoEntre1500e2000() throws Exception {
		final StringBuilder content = new StringBuilder("{")
				.append("\"nome\": \"Pedro\",")
				.append("\"cpf\": \"99619998911\",")
				.append("\"idade\": 68,")
				.append("\"sexo\": \"m\",")
				.append("\"estadoCivil\": \"casado\",")
				.append("\"estado\": \"sc\",")
				.append("\"dependentes\": \"3\",")
				.append("\"renda\": \"8000\"")
				.append("}");
		mockMvc.perform(MockMvcRequestBuilders.post("/cliente/analisar")
				.content(content.toString())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value("Aprovado"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.limite").value("entre-1500-2000")).andReturn();
	}

}
