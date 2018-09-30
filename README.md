# Crud em Java
<strong>Sistema simples em Java para treinar CRUD</strong>

<p><strong>Regras do CRUD:</strong></p>
<p><strong>R1</strong> – Ao iniciar a tela, o programa deve apresentar na listagem (JList) todos os nomes de usuários já existentes no banco de dados. Em formato de lista (um abaixo do outro).</p>
<p><strong>R2</strong> – Ao final das seguintes operações: Salvar, Editar (Colocar em Edição + Salvar) ou Excluir. O programa deve apresentar comportamento similar à R1.</p>
<p><strong>R3</strong> – Ao clicar em “Novo” o programa deve limpar os campos de entrada de dados (Usuário e Senha) e colocar o cursor dentro do campo Usuário.</p>
<p><strong>R4</strong> – Ao clicar em “Salvar” (no caso de ser um novo registro), o programa deve validar o seguinte: a) se os campos Usuário e Senha estiverem vazios o programa deve mostrar uma mensagem de preenchimento obrigatório e não permitir o salvamento do registro. b) se o campo Usuário já existe no banco de dados. Ou seja, o Usuário deve ser único no programa. c) Imediatamente após o salvamento o programa deve apresentar comportamento similar à R1.</p>
<p><strong>R5</strong> - Ao clicar em “Salvar” (no caso de ser uma edição de registro), o programa deve validar o seguinte: a) se os campos Usuário e Senha estiverem vazios o programa deve mostrar uma mensagem de preenchimento obrigatório e não permitir o salvamento do registro. b) se o campo Usuário já existe no banco de dados. Ou seja, o Usuário deve ser único no programa. c) Imediatamente após o salvamento o programa deve apresentar comportamento similar à R1.</p> 
<p><strong>R6</strong> – Ao clicar em “Colocar em Edição” o programa deve validar se existe algum registro marcado na listagem. Caso não exista o programa deve emitir um aviso para o operador selecionar algum registro na lista. Caso o registro esteja marcado na lista, o programa deve popular os campos Usuário e Senha com os valores referentes ao registro marcado na listagem.</p>
<p><strong>R7</strong> – Ao clicar em “Excluir” o programa deve validar se existe algum registro marcado na listagem. Caso não exista, o programa deve emitir um aviso para o operador selecionar algum registro na lista. Caso o registro esteja selecionado, o programa deve perguntar se o registro deverá mesmo ser excluído. Caso o usuário confirme, o programa deve excluir o registro do banco de dados e apresentar comportamento similar à R1. Caso o usuário desista da exclusão, o programa não deve continuar a operação.</p> 
<p><strong>R8</strong> – O programa não deve permitir cadastramento de usuários com o mesmo nome (campo Usuário).</p> 
<p><strong>Regra-Geral:</strong> Toda a comunicação entre a tela (view) e os objetos de negócio (model), deve ser feita por meio de um objeto do tipo (controller): classe Java que implementa ActionListener.</p>
