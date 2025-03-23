Tutorial Detalhado: Teste de Carga em hortti.com com "Distributed Load Testing on AWS"

1. Configuração Inicial:

Conta AWS:
Certifique-se de que sua conta AWS tenha permissões suficientes para criar recursos do CloudFormation, S3, ECS, CloudWatch e outros serviços necessários.
Considere usar uma conta AWS dedicada para testes de carga para evitar interferências com ambientes de produção.
Acesso ao Console AWS:
Use um navegador atualizado e seguro para acessar o console da AWS.
Considere usar a autenticação multifator (MFA) para maior segurança.
Região AWS:
Selecione uma região AWS que esteja geograficamente próxima dos usuários que você deseja simular.
Isso ajudará a simular cenários de carga mais realistas.
2. Implantação do "Distributed Load Testing on AWS":

AWS Solutions Library:
Navegue até a AWS Solutions Library digitando "AWS Solutions Library" na barra de pesquisa do console da AWS.
Pesquise por "Distributed Load Testing on AWS" e clique na solução.
CloudFormation:
Leia atentamente a documentação da solução para entender os parâmetros da pilha CloudFormation.
Configure os parâmetros da pilha, como o nome da pilha, o tipo de instância do ECS e o número de nós de teste.
Revise as políticas do IAM que serão criadas pelo CloudFormation e certifique-se de que elas atendam aos seus requisitos de segurança.
Marque a caixa de seleção para reconhecer que o CloudFormation pode criar recursos do IAM.
Clique em "Criar pilha" e aguarde a conclusão da implantação.
Verifique os eventos da pilha do CloudFormation para solucionar possíveis erros de implantação.
Quando a pilha for criada, veja na aba outputs os dados de conexão do sistema de testes.
3. Criação de um Script JMeter:

Apache JMeter:
Faça o download e instale a versão mais recente do Apache JMeter.
Considere usar a versão da linha de comando do JMeter para testes de carga em grande escala.
Crie um Plano de Teste:
Abra o JMeter e crie um novo plano de teste.
Thread Group:
Configure o "Thread Group" com um número de usuários que represente a carga esperada.
Use um tempo de ramp-up gradual para simular o aumento gradual do tráfego.
Defina a duração do teste para coletar dados suficientes para análise.
HTTP Request:
Configure o "HTTP Request" com o método HTTP correto (GET, POST, etc.).
Adicione cabeçalhos HTTP relevantes, como User-Agent e Accept-Language.
Adicione parâmetros de consulta ou corpo de solicitação, se necessário.
Listeners:
Adicione o "View Results Tree" para visualizar os resultados de solicitações individuais.
Adicione o "Aggregate Report" para gerar estatísticas de desempenho resumidas.
Adicione o "Graph Results" para visualizar os resultados graficamente.
Salve o Script:
Salve o plano de teste JMeter com um nome descritivo (por exemplo, hortti-load-test.jmx).
4. Upload do Script para o S3:

Amazon S3:
Crie um bucket S3 na mesma região AWS em que você implantou o "Distributed Load Testing on AWS".
Configure as permissões do bucket para permitir que o "Distributed Load Testing on AWS" acesse o arquivo .jmx.
Upload do Arquivo .jmx:
Use o console S3 ou a AWS CLI para fazer o upload do arquivo .jmx para o bucket.
Anote o URL do arquivo .jmx no S3.
5. Execução do Teste de Carga:

Console da Solução:
Utilize os dados da aba outputs da pilha do Cloudformation criada, para acessar a console da solução.
Configure os testes:
Insira o URL do arquivo .jmx presente no S3, no campo correspondente dentro da tela de configuração de testes.
configure os demais campos de configuração, para os valores desejados.
Inicie o teste.
6. Monitoramento e Análise:

Amazon CloudWatch:
Use o CloudWatch para monitorar métricas como CPU, memória, latência e taxa de erros durante o teste.
Crie painéis personalizados para visualizar as métricas relevantes.
configure alarmes do CloudWatch, caso queira ter notificações de comportamentos inesperados.
Relatórios JMeter:
Analise os resultados no "Aggregate Report" para identificar gargalos de desempenho e áreas de melhoria.
Analise o "View Results Tree", para analise detalhada de erros.
Utilize o "Graph Results", para ter uma visão geral do comportamento dos testes.
Analise AWS:
Analise os dados dos demais recursos aws que foram utilizados durante os testes, principalmente os dados de utilização de rede da maquina, onde a página está hospedada.
Melhores Práticas:

Execute testes de carga em ambientes de teste que sejam semelhantes aos ambientes de produção.
Varie a carga de teste para simular diferentes cenários de tráfego.
Automatize os testes de carga e integre-os ao pipeline de CI/CD.
Colabore com as equipes de desenvolvimento e operações para analisar os resultados dos testes e implementar melhorias.
