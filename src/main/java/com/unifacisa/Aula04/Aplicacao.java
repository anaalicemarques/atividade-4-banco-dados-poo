package com.unifacisa.Aula04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Aplicacao {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		 
		String jsonOutput = mapper.writeValueAsString(getPessoas());
	   
		// Serialize
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, getPessoas());
		System.out.println(writer);

		// Deserialize
		String jsonInput = "[{\"id\":1,\"nome\":\"Lucas\",\"cpf\":18255608499, \"estadoCivil\":\"Solteiro\"},{\"id\":2,\"nome\":\"Maria\",\"cpf\":11229856982,\"estadoCivil\":\"Casada\"},{\"id\":3,\"nome\":\"Jose\",\"cpf\":45657809121,\"estadoCivil\":\"Divorciado\"}]";
		List<Pessoa> p = mapper.readValue(jsonInput, new TypeReference<List<Pessoa>>() {
		});
		System.out.println("Pessoa: " + p);

		String nomeArquivo = "arquivo.json";

		writeDataToJson(nomeArquivo, jsonOutput);

	}
	
	private static void writeDataToJson(String nomeArquivo, String jsonData) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
				writer.write(jsonData);
				System.out.println("Dados salvos com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<Pessoa> getPessoas() {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		Pessoa p1 = new Pessoa();
		p1.setId(1);
		p1.setNome("Lucas");
		p1.setCpf(18255608499L);
		p1.setEstadoCivil("Solteiro");

		Pessoa p2 = new Pessoa();
		p2.setId(2);
		p2.setNome("Maria");
		p2.setCpf(11229856982L);
		p2.setEstadoCivil("Casada");

		Pessoa p3 = new Pessoa();
		p3.setId(3);
		p3.setNome("Jose");
		p3.setCpf(45657809121L);
		p3.setEstadoCivil("Divorciado");

		pessoas.add(p1);
		pessoas.add(p2);
		pessoas.add(p3);

		return pessoas;

	}
}