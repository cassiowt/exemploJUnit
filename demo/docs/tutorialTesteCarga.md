# Tutorial Detalhado: Teste de Carga em hortti.com com "Distributed Load Testing on AWS"

## 1. Configuração Inicial

### Conta AWS
- Verifique se sua conta AWS possui permissões para criar recursos como CloudFormation, S3, ECS, CloudWatch, etc.
- Recomenda-se o uso de uma conta AWS dedicada a testes de carga, evitando impactos em ambientes de produção.

### Acesso ao Console AWS
- Utilize um navegador atualizado e seguro para acessar o console AWS.
- Ative a autenticação multifator (MFA) para segurança adicional.

### Região AWS
- Escolha uma região próxima dos usuários que você deseja simular.
- Isso ajuda a representar cenários de carga mais realistas.

---

## 2. Implantação do "Distributed Load Testing on AWS"

### AWS Solutions Library
- Acesse a AWS Solutions Library via barra de pesquisa no console AWS.
- Pesquise por **"Distributed Load Testing on AWS"** e selecione a solução.

### CloudFormation
- Leia a documentação da solução para compreender os parâmetros da pilha.
- Configure os seguintes campos:
  - Nome da pilha
  - Tipo de instância ECS
  - Número de workers (nós de teste)
- Revise as permissões IAM que serão criadas.
- Marque a opção que autoriza o CloudFormation a criar recursos IAM.
- Clique em **"Criar pilha"** e acompanhe os eventos para monitorar a implantação.
- Quando finalizada, acesse a aba **"Outputs"** para obter as URLs e credenciais da solução.

---

## 3. Criação de um Script JMeter

### Apache JMeter
- Baixe e instale a última versão do Apache JMeter: [https://jmeter.apache.org](https://jmeter.apache.org)
- Para grandes volumes, prefira a versão de linha de comando (non-GUI).

### Plano de Teste

#### Thread Group
- Configure o número de usuários virtuais.
- Defina tempo de ramp-up gradual.
- Estabeleça duração total do teste.

#### HTTP Request
- Configure o método HTTP adequado (GET, POST, etc.).
- Adicione headers como `User-Agent` e `Accept-Language`.
- Inclua parâmetros ou corpo da requisição, se necessário.

#### Listeners
- **View Results Tree**: visualiza detalhes de cada requisição.
- **Aggregate Report**: mostra estatísticas consolidadas.
- **Graph Results**: visualiza desempenho em tempo real.

#### Salvamento
- Salve o plano como `hortti-load-test.jmx`.

---

## 4. Upload do Script para o S3

### Amazon S3
- Crie um bucket na mesma região do "Distributed Load Testing".
- Configure as permissões para permitir leitura do arquivo pela solução.

### Upload do Arquivo
- Use o console S3 ou AWS CLI:
```bash
aws s3 cp hortti-load-test.jmx s3://nome-do-bucket/
```
- Anote a URL completa do arquivo `.jmx`.

---

## 5. Execução do Teste de Carga

### Console da Solução
- Acesse a URL da aplicação distribuída de testes (disponível em **Outputs** da pilha).

### Configuração do Teste
- Informe a URL do arquivo `.jmx` armazenado no S3.
- Configure:
  - Duração do teste
  - Quantidade de threads
  - Intervalo de ramp-up
- Inicie o teste.

---

## 6. Monitoramento e Análise

### Amazon CloudWatch
- Monitore métricas: CPU, memória, latência, erros.
- Crie dashboards personalizados.
- Configure **alarmes** para comportamentos anormais.

### Relatórios JMeter
- **Aggregate Report**: avalia médias, erros e throughput.
- **View Results Tree**: identifica falhas individuais.
- **Graph Results**: visualiza comportamento ao longo do tempo.

### Análise Complementar
- Avalie logs de rede, ECS e serviços relacionados no CloudWatch.
- Compare o desempenho da aplicação com cenários de carga esperados.

---

## Melhores Práticas

- Execute os testes em ambientes semelhantes ao de produção.
- Varie a carga para simular diferentes cenários de uso.
- Integre os testes ao pipeline de CI/CD.
- Compartilhe os resultados com devs, ops e QA para melhorias conjuntas.

---

**Pronto!** Agora você está apto a executar testes de carga em hortti.com usando uma arquitetura escalável e distribuída da AWS. 🚀
