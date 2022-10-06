# agendamentoemail
Curso de EJB com Java EE 8: API Rest com WildFly 15

abrir o cmd e acessar a pasta bin do wildfly

para conectar no jboss:
jboss-cli.bat --connect


Os comandos que o instrutor executa são:
Para adicionar o JAR como module use:

module add --name=com.mysql --resources=/D:/Software/jars/mysql-connector-java-8.0.30.jar --dependencies=javax.api,javax.transaction.api


/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)


No comando abaixo, que define o datasource, altere o usuário e a senha baseado na sua configuração do banco:

data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=root --password=root --min-pool-size=10 --max-pool-size=20

*******

comandos para configurar o mailtrap na aplicação:

abrir o cmd e acessar a pasta bin do wildfly

para conectar no jboss:
jboss-cli.bat --connect

/subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)

--

/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)

--

/subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=fbd07d4209b3de, password=5e4330a83c3571, tls=true)



comandos para configurar um repositório de fila no Wildfly

*******

abrir o cmd e acessar a pasta bin do wildfly

para conectar no jboss:
jboss-cli.bat --connect


jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue

--

E os atributos que o instrutor copia para a classe AgendamentoEmailTimer são os seguintes:

@Inject
@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
private JMSContext context;



@Resource(mappedName = "java:/jms/queue/EmailQueue")
private Queue queue;

-- para ver a fila
jms-queue list-messages --queue-address=EmailQueue
