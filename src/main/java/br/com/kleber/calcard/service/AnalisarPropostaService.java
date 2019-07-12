package br.com.kleber.calcard.service;

import org.springframework.stereotype.Service;

import br.com.kleber.calcard.dto.ClienteDTO;
import br.com.kleber.calcard.dto.PropostaResultDTO;
import br.com.kleber.calcard.entity.Cliente;
import br.com.kleber.calcard.exception.AnalisarPropostaException;
import br.com.kleber.calcard.repository.ClienteRepository;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class AnalisarPropostaService {

	public final static String RESULTADO_APROVADO = "Aprovado";
	public final static String RESULTADO_NEGADO = "Negado";

	public final static String LIMITE_REPROVADO_PELA_POLITICA_DE_CREDITO = "reprovado-pela-política-de-crédito";
	public final static String LIMITE_RENDA_BAIXA = "renda-baixa";
	public final static String LIMITE_ENTRE_100_500 = "entre-100-500";
	public final static String LIMITE_ENTRE_500_1000 = "entre-500-1000";
	public final static String LIMITE_ENTRE_1000_1500 = "entre-1000-1500";
	public final static String LIMITE_ENTRE_1500_2000 = "entre-1500-2000";
	public final static String LIMITE_SUPERIOR_2000 = "superior-2000";

	public final static String RENDA_INFERIOR_1000 = "<1000";
	public final static String RENDA_ENTRE_1000_3000 = "1000-3000";
	public final static String RENDA_SUPERIOR_3000 = ">3000";

	public final static String DEPENDENTES_0 = "0";
	public final static String DEPENDENTES_ENTRE_1_3 = "1-3";
	public final static String DEPENDENTES_MAIOR_3 = ">3";

	public final static String IDADE_MENOR_20 = "<20";
	public final static String IDADE_ENTRE_20_39 = "20-39";
	public final static String IDADE_MAIOR_IGUAL_40 = ">=40";

	public final static String SEXO_MASCULINO = "M";
	public final static String SEXO_FEMINIMO = "F";


	private final ClienteRepository clienteRepository;

	public AnalisarPropostaService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public PropostaResultDTO analisar(ClienteDTO clienteDTO) throws AnalisarPropostaException {

		try {

			DataSource ds = new DataSource("src/main/resources/analise.arff");
			Instances instance = ds.getDataSet();

			// INDICE DA CLASSE A SER ANALISDA
			instance.setClassIndex(5);

			//CLASSIFICADOR
			NaiveBayes nb = new NaiveBayes();
			nb.buildClassifier(instance);

			//CRIACAO DO OBJETO A SER CLASSIFICADO
			Instance novaProposta = new DenseInstance(6);
			novaProposta.setDataset(instance);

			//SEXO
			novaProposta.setValue(0, clienteDTO.getSexo().toUpperCase().equals(SEXO_MASCULINO) ? SEXO_MASCULINO : SEXO_FEMINIMO);

			//IDADE
			String idade;
			if(clienteDTO.getIdade() < 20) {
				idade = IDADE_MENOR_20;
			} else if (clienteDTO.getIdade() >= 40) {
				idade = IDADE_MAIOR_IGUAL_40;
			} else {
				idade = IDADE_ENTRE_20_39;
			}
			novaProposta.setValue(1, idade);

			//DEPENDENTES
			String dependentes;
			if (clienteDTO.getDependentes() == 0) {
				dependentes = DEPENDENTES_0;
			} else if (clienteDTO.getDependentes() > 3) {
				dependentes = DEPENDENTES_MAIOR_3;
			} else {
				dependentes = DEPENDENTES_ENTRE_1_3;
			}
			novaProposta.setValue(2, dependentes);

			//RENDA
			String renda;
			if (clienteDTO.getRenda() < 1000) {
				renda = RENDA_INFERIOR_1000;
			} else if (clienteDTO.getRenda() > 3000) {
				renda = RENDA_SUPERIOR_3000;
			} else {
				renda = RENDA_ENTRE_1000_3000;
			}
			novaProposta.setValue(3, renda);

			int indice = 0;
			double maiorProbabilidade = 0;

			//CALCULAR PROBABILIDADES DOS LIMITES
			double[] distributionForInstance = nb.distributionForInstance(novaProposta);


			for (int i = 0; i < distributionForInstance.length; i++) {
				if(distributionForInstance[i] > maiorProbabilidade) {
					maiorProbabilidade = distributionForInstance[i];
					indice = i;
				}
			}

			String limite = novaProposta.attribute(5).value(indice);
			String resultado;
			switch (limite) {
			case LIMITE_RENDA_BAIXA:
			case LIMITE_REPROVADO_PELA_POLITICA_DE_CREDITO:
				resultado = RESULTADO_NEGADO;
				break;
			default:
				resultado = RESULTADO_APROVADO;
				break;
			}

			Cliente cliente = new Cliente(clienteDTO, resultado, limite);
			salvarProposta(cliente);
			return new PropostaResultDTO(resultado, limite);
		} catch (Exception e) {
			throw new AnalisarPropostaException();
		}
	}

	private void salvarProposta(Cliente cliente) {
		clienteRepository.save(cliente);
	}

}
