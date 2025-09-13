package com.testSpring.Tests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpring.Tests.service.GeminiService;

@CrossOrigin(origins = "http://127.0.0.1:5500") 
@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private static final String API_KEY = "AIzaSyCauJcpq47pg8rD3X1FRWIzZ3MWkkmUCxc";
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent?key=" + API_KEY;
   
    @Autowired
    private GeminiService geminiService;

    @PostMapping("/generate")
    public String generate(@RequestBody PromptRequest request) throws Exception {
        return geminiService.generate(request.getModel(), request.getPrompt());
    }

    @PostMapping("/contribute")
    public String contribuinte(@RequestBody ContribuinteRequest req) throws Exception {
        String prompt = String.format(
            "Dados do Contribuinte: Olá, sou um senhor de idade e preciso de uma ajuda com meus impostos. Para isso, quero que você seja meu contador. Preciso que você olhe todos os meus dados e me diga quanto eu tenho que pagar de imposto por mês e por ano. Tenho %d anos, meu CPF é %s e sou %s. Minha renda principal é a minha aposentadoria de R$ %.2f por mês. Além disso, se eu tiver outra renda como %s, eu te aviso. Eu também tenho uma casa que fica na cidade de %s e um carro, que é um %s. Se eu tiver alguma doença grave, eu te aviso aqui: %s. Com todas essas informações, por favor, me diga: eu tenho que pagar alguma coisa? E se eu tenho algum desconto ou não pago nada por ser mais velho?",
            req.getIdade(), req.getCpf(), req.getEstadoCivil(), req.getAposentadoria(), req.getOutrasRendas(), req.getCidade(), req.getCarro(), req.getDoencaGrave()
        );
        return geminiService.generate(req.getModel(), prompt);
    }

    @PostMapping("/analyse")
    public String analyse(@RequestBody AnaliseRequest req) throws Exception {
        String prompt = String.format(
            "Análise e Resposta: Baseado na legislação fiscal brasileira vigente, analise os dados fornecidos e calcule as alíquotas de impostos. Inclua as isenções aplicáveis a pessoas com mais de 65 anos e em casos de doenças graves. Sua resposta deve conter APENAS as porcentagens de imposto aplicáveis a cada item, sem qualquer texto ou explicação. Apresente as informações em uma lista simples, com o nome do imposto e sua porcentagem. Para impostos isentos, indique 0%%."
        );
        return geminiService.generate(req.getModel(), prompt + "\n\n" + req.getDados());
    }

    @PostMapping("/finantialPlan")
    public String finantialPlan(@RequestBody PlanoFinanceiroRequest req) throws Exception {
        String prompt = String.format(
            "Você é um consultor financeiro e analista tributário, especializado em planejamento para a terceira idade. Sua tarefa é analisar o cenário de um cliente que possui uma renda mensal de *%.2f* e enfrenta uma despesa de grande porte no valor de *R$ %.2f*, referente a **%s**. Se houver alguma condição especial aplicável, como um gasto médico que possa ser abatido do Imposto de Renda, isso deve ser considerado. Com base nesses dados, sua resposta deve ser um plano financeiro conciso, contendo apenas três informações: o **Valor Total* da despesa (sinalizando se é possível *abater do Imposto de Renda), o **Valor da Parcela Mensal* ideal para o cliente (que não comprometa mais de 30%% de sua renda), e o *Tempo para Quitação* estimado em meses ou anos, sem qualquer texto adicional ou explicação.",
            req.getRendaTotal(), req.getDespesaTotal(), req.getTipoDespesa()
        );
        return geminiService.generate(req.getModel(), prompt);
    }

    
    public static class ContribuinteRequest {
        private String model;
        private int idade;
        private String cpf;
        private String estadoCivil;
        private double aposentadoria;
        private String outrasRendas;
        private String cidade;
        private String carro;
        private String doencaGrave;
        
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public int getIdade() { return idade; }
        public void setIdade(int idade) { this.idade = idade; }
        public String getCpf() { return cpf; }
        public void setCpf(String cpf) { this.cpf = cpf; }
        public String getEstadoCivil() { return estadoCivil; }
        public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
        public double getAposentadoria() { return aposentadoria; }
        public void setAposentadoria(double aposentadoria) { this.aposentadoria = aposentadoria; }
        public String getOutrasRendas() { return outrasRendas; }
        public void setOutrasRendas(String outrasRendas) { this.outrasRendas = outrasRendas; }
        public String getCidade() { return cidade; }
        public void setCidade(String cidade) { this.cidade = cidade; }
        public String getCarro() { return carro; }
        public void setCarro(String carro) { this.carro = carro; }
        public String getDoencaGrave() { return doencaGrave; }
        public void setDoencaGrave(String doencaGrave) { this.doencaGrave = doencaGrave; }
    }

    public static class AnaliseRequest {
        private String model;
        private String dados;
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getDados() { return dados; }
        public void setDados(String dados) { this.dados = dados; }
    }

    public static class PlanoFinanceiroRequest {
        private String model;
        private double rendaTotal;
        private double despesaTotal;
        private String tipoDespesa;
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public double getRendaTotal() { return rendaTotal; }
        public void setRendaTotal(double rendaTotal) { this.rendaTotal = rendaTotal; }
        public double getDespesaTotal() { return despesaTotal; }
        public void setDespesaTotal(double despesaTotal) { this.despesaTotal = despesaTotal; }
        public String getTipoDespesa() { return tipoDespesa; }
        public void setTipoDespesa(String tipoDespesa) { this.tipoDespesa = tipoDespesa; }
    }
    public static class PromptRequest {
        private String model;
        private String prompt;
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }
    }
}
