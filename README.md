## Arquitetura de solução

<img width="3289" height="1867" alt="Sem título-2025-10-31-1025" src="https://github.com/user-attachments/assets/aab86e27-ac35-49ba-8b56-0d03a971ae91" />

---

## 🛍️ Catálogo de Produtos

- [x] Criar endpoint de listagem paginada de produtos - área de conhecimento (back end)
- [ ] Implementar cache com Redis para listagem de produtos - área de conhecimento (infra)
- [ ] Criar interface de catálogo com paginação - área de conhecimento (front end)
- [ ] Integrar catálogo com API Gateway e Eureka - área de conhecimento (back end)

---

## 🔍 Filtro e Busca

- [x] Criar endpoint para filtragem por categoria - área de conhecimento (back end)
- [ ] Criar endpoint para busca por nome - área de conhecimento (back end)
- [ ] Implementar UI de filtros e campo de busca - área de conhecimento (front end)

---

## 🛒 Carrinho de Compras

- [ ] Criar serviço de carrinho com endpoints para adicionar, remover e listar itens - área de conhecimento (back end)
- [ ] Persistir carrinho com PostgreSQL - área de conhecimento (infra)
- [ ] Criar interface de carrinho com resumo do pedido - área de conhecimento (front end)
- [ ] Integrar carrinho com serviço de autenticação via OpenFeign - área de conhecimento (back end)

---

## 🔐 Autenticação e Segurança

- [x] Implementar login e cadastro com JWT e 2FA - área de conhecimento (back end)
- [x] Configurar Spring Security no serviço com jwt - área de conhecimento (back end)
- [ ] Persistir usuários com PostgreSQL e Redis - área de conhecimento (infra)
- [ ] Criar interface de login e cadastro - área de conhecimento (front end)

---

## 💳 Pagamento

- [x] Integrar serviço de pagamento com Mercado Pago via OpenFeign - área de conhecimento (back end)
- [ ] Criar eventos Kafka para pagamento preferencial, confirmado e negado - área de conhecimento (back end)
- [ ] Criar interface de finalização de compra - área de conhecimento (front end)

---

## 📣 Notificações

- [x] Criar serviço de notificação com integração Twilio - área de conhecimento (back end)
- [ ] Consumir eventos Kafka de pagamento confirmado - área de conhecimento (back end)
- [ ] Enviar SMS de confirmação de compra - área de conhecimento (infra)

---

## ⚙️ Infraestrutura e Comunicação

- [ ] Configurar Spring Cloud Gateway e Eureka para roteamento e descoberta de serviços - área de conhecimento (infra)
- [ ] Configurar Kafka como plataforma de eventos - área de conhecimento (infra)
- [ ] Configurar Redis para cache de produtos e autenticação - área de conhecimento (infra)
- [ ] Configurar PostgreSQL para persistência de dados - área de conhecimento (infra)

---
