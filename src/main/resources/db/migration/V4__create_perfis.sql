insert into "bct".tb_operacao(id, inclusao, status, codigo, uri, descricao)
values(1, '2020/09/16', 1, 'usuario-clients-buscartodos', '/usuario/v1/usuarios', 'Buscar todos os clients');

insert into "bct".tb_operacao(id, inclusao, status, codigo, uri, descricao)
values(2, '2020/09/16', 1, 'usuario-clients-buscarporid', '/usuario/v1/usuarios/{id}', 'Buscar client por id');

insert into "bct".tb_operacao(id, inclusao, status, codigo, uri, descricao)
values(3, '2020/09/16', 1, 'usuario-clients-incluir', '/usuario/v1/usuarios', 'Incluir client');

insert into "bct".tb_operacao(id, inclusao, status, codigo, uri, descricao)
values(4, '2020/09/16', 1, 'usuario-clients-atualizar', '/usuario/v1/usuarios', 'Atualizar um client');

insert into "bct".tb_operacao(id, inclusao, status, codigo, uri, descricao)
values(5, '2020/09/16', 1, 'usuario-clients-excluir', '/usuario/v1/usuarios/{id}', 'Deletar um client');

insert into "bct".tb_perfil(id, inclusao, status, nome)
values(1, '2020/04/14', 1, 'ADMIN');

insert into "bct".tb_perfil(id, inclusao, status, nome)
values(2, '2020/04/14', 1, 'USUARIO');
