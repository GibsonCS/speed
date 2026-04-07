# Requisitos Funcionais e Regras de Negócio - Speed Service API

## 📋 Índice

1. [Requisitos Funcionais](#requisitos-funcionais)
2. [Regras de Negócio](#regras-de-negócio)

---

## 🎯 Requisitos Funcionais

### RF-001: Cadastro de Usuário

**Descrição:** Permitir que novos usuários se registrem no sistema

**Ator:** Usuário não autenticado

**Pré-condições:**
- Email não existe no sistema
- Todos os campos obrigatórios foram preenchidos

**Fluxo Principal:**
1. Usuário fornece: nome, sobrenome, email, senha
2. Sistema valida os dados
3. Sistema cria usuário com role "USER" por padrão
4. Sistema retorna ID do usuário criado

**Fluxo de Erro:**
- Email já cadastrado → HTTP 400
- Email inválido → HTTP 400
- Senha fraca → HTTP 400
- Nome muito curto/longo → HTTP 400

**Pós-condição:** 
- Usuário aparece no banco de dados com role "USER"

**Endpoint:** `POST /users`  
**Status:** ✅ Implementado

---

### RF-002: Obter Usuário por ID

**Descrição:** Recuperar informações de um usuário específico

**Ator:** Usuário autenticado

**Pré-condições:**
- Usuário com ID fornecido existe
- Usuário autenticado tem permissão

**Fluxo Principal:**
1. Usuário fornece ID do usuário desejado
2. Sistema localiza usuário no banco
3. Sistema retorna dados do usuário (sem senha)

**Fluxo de Erro:**
- Usuário não encontrado → HTTP 404
- ID inválido → HTTP 400

**Pós-condição:** Dados do usuário retornados com sucesso

**Endpoint:** `GET /users/{id}`  
**Status:** Parcialmente implementado

---

### RF-003: Atualizar Usuário

**Descrição:** Permitir que um usuário atualize seus dados

**Ator:** Usuário autenticado

**Pré-condições:**
- Usuário existe no sistema
- Usuário está autenticado

**Fluxo Principal:**
1. Usuário fornece: nome, sobrenome, email (campos atualizáveis)
2. Sistema valida novos dados
3. Sistema verifica se novo email já existe (se foi alterado)
4. Sistema atualiza dados do usuário
5. Sistema retorna usuário atualizado

**Fluxo de Erro:**
- Usuário não encontrado → HTTP 404
- Email já existe → HTTP 400
- Dados inválidos → HTTP 400

**Pós-condição:** Usuário atualizado no banco de dados

**Endpoint:** `PUT /users/{id}` ou `PATCH /users/{id}`  
**Status:** Parcialmente implementado

---

### RF-004: Deletar Usuário

**Descrição:** Permitir que um usuário delete sua conta

**Ator:** Usuário autenticado

**Pré-condições:**
- Usuário existe no sistema
- Usuário está autenticado

**Fluxo Principal:**
1. Usuário fornece ID da conta a deletar
2. Sistema verifica existência do usuário
3. Sistema deleta usuário
4. Sistema retorna sucesso (HTTP 204)

**Fluxo de Erro:**
- Usuário não encontrado → HTTP 404

**Pós-condição:** Usuário removido do sistema

**Endpoint:** `DELETE /users/{id}`  
**Status:** Parcialmente implementado

---

### RF-005: Cadastro de Customer

**Descrição:** Permitir que usuários cadastrem clientes (perfis de empresa)

**Ator:** Usuário autenticado

**Pré-condições:**
- Usuário autenticado
- CNPJ não existe no sistema

**Fluxo Principal:**
1. Usuário fornece: CNPJ, nome empresa, telefone, email, endereço
2. Sistema valida CNPJ (formato e dígitos verificadores)
3. Sistema valida endereço
4. Sistema cria customer vinculado ao usuário
5. Sistema retorna ID do customer

**Pós-condição:** Customer criado e vinculado ao usuário

**Endpoint:** `POST /customers`  
**Status:** ⏳ Em desenvolvimento

---

### RF-006: Criar Pedido com Múltiplos Serviços

**Descrição:** Permitir que usuários criem pedidos contendo múltiplos serviços

**Ator:** Usuário autenticado

**Pré-condições:**
- Usuário tem pelo menos um customer cadastrado
- Serviços fornecidos existem no sistema

**Fluxo Principal:**
1. Usuário cria pedido vazio (status DRAFT)
2. Usuário adiciona items (serviços) ao pedido
3. Usuário submete pedido para análise (muda status para IN_ANALYSIS)
4. Sistema calcula total
5. Sistema gera histórico da transação
6. Sistema retorna pedido criado

**Validações:**
- Data de execução ≥ hoje + 1 dia
- Preço > 0
- Não pode adicionar item em pedido já confirmado/pago/cancelado
- Não pode ter items duplicados (mesmo serviceId)

**Pós-condição:** Pedido em status "IN_ANALYSIS" aguardando confirmação

**Endpoint:** `POST /orders`  
**Status:** ⏳ Em desenvolvimento

---

### RF-007: Confirmar Pedido

**Descrição:** Permitir que gerentes confirmem um pedido em análise

**Ator:** Gerente/Admin

**Pré-condições:**
- Pedido existe
- Pedido está em status IN_ANALYSIS

**Fluxo Principal:**
1. Gerente recebe pedido para análise
2. Gerente revisa items e preços
3. Gerente confirma pedido
4. Sistema muda status para CONFIRMED

**Pós-condição:** Pedido confirmado, aguardando pagamento

**Endpoint:** `PATCH /orders/{id}/confirm`  
**Status:** ⏳ Em desenvolvimento

---

### RF-008: Pagar Pedido

**Descrição:** Registrar pagamento de um pedido confirmado

**Ator:** Sistema de Pagamento / Usuário

**Pré-condições:**
- Pedido existe
- Pedido está em status CONFIRMED

**Fluxo Principal:**
1. Usuário/Sistema envia confirmação de pagamento
2. Sistema verifica status do pedido
3. Sistema muda status para PAID
4. Sistema registra data/hora do pagamento

**Pós-condição:** Pedido pago, pronto para execução

**Endpoint:** `PATCH /orders/{id}/pay`  
**Status:** ⏳ Em desenvolvimento

---

## ✅ Regras de Negócio

### RN-001: Validação de Usuário

**Descrição:** Define as regras para cadastro e manutenção de usuários

**Regras:**
- Email deve ser único no sistema
- Email deve ter formato válido: `^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z]{2,})+$`
- Senha deve ter entre 6-12 caracteres
- Nome e Sobrenome: 3-20 caracteres, apenas letras + acentos (sem números/caracteres especiais)
- Todo novo usuário recebe role "USER" por padrão
- Senha deve ser criptografada com BCrypt antes de persistir
- Senha nunca é retornada em respostas da API

**Implementação:** `User.java`, `Email.java`, `Name.java` (Value Objects)

**Status:** ✅ Parcialmente implementado (sem criptografia)

---

### RN-002: Validação de Customer

**Descrição:** Define as regras para cadastro e manutenção de clientes

**Regras:**
- CNPJ deve ser único no sistema
- CNPJ deve ter formato válido: `XX.XXX.XXX/XXXX-XX`
- Dígitos verificadores do CNPJ devem estar corretos
- Telefone deve ter 11 dígitos (Brasil)
- Email deve ser válido (mesma regra do RN-001)
- Endereço é obrigatório com todos os campos:
  - Rua: 5-100 caracteres
  - Bairro: 3-50 caracteres
  - UF: 2 caracteres, sigla válida (SP, RJ, MG, etc)
  - CEP: formato XXXXX-XXX (8 dígitos)
- Customer é vinculado permanentemente ao usuário que criou
- Um usuário pode ter múltiplos customers

**Implementação:** `Customer.java`, `Address.java`, `Phone.java`, `Email.java`

**Status:** ⏳ Em desenvolvimento

---

### RN-003: Criação e Gerenciamento de Pedido

**Descrição:** Define as regras para criar e gerenciar pedidos

**Regras:**
- Customer deve existir e pertencer ao usuário autenticado
- Data de execução deve ser ≥ hoje + 1 dia (não pode agendar para hoje/passado)
- Preço cobrado deve ser > R$ 0,00 e máximo R$ 999.999,99
- Usar BigDecimal com 2 casas decimais para preços
- Não pode adicionar serviço duplicado no mesmo pedido
- Pedido inicia com status DRAFT
- Ao submeter, status muda para IN_ANALYSIS
- Cada OrderItem tem status independente: PENDING, EXECUTED, CANCELED
- Estados do Pedido: DRAFT → IN_ANALYSIS → (CONFIRMED ou REJECTED) → PAID → EXECUTED
- De qualquer estado (exceto PAID): pode cancelar → CANCELED
- Pedido PAID não pode ser cancelado
- Preço capturado no momento da contratação é imutável

**Implementação:** `Order.java`, `OrderItem.java`, `OrderStatus.java`, `OrderItemStatus.java`

**Status:** ⏳ Em desenvolvimento

---

### RN-004: Histórico e Auditoria

**Descrição:** Define como registrar e rastrear alterações no sistema

**Regras:**
- Toda entidade deve ter `created_at` (data de criação)
- Toda entidade mutável deve ter `updated_at` (data de última atualização)
- Registrar qual usuário criou/atualizou quando aplicável
- Manter histórico completo (nunca deletar dados, apenas marcar como inativo)
- Preço capturado no momento da contratação é imutável (histórico de preços)
- Mudanças de status de pedido são auditadas

**Implementação:** `@CreatedDate`, `@LastModifiedDate` (Spring Data JPA Auditing)

**Status:** ✅ Implementado

---

### RN-005: Autorização e Acesso

**Descrição:** Define regras de acesso aos dados

**Regras:**
- Usuário não autenticado pode apenas se registrar (RF-001)
- Usuário autenticado pode visualizar/atualizar/deletar apenas sua própria conta
- Usuário autenticado pode visualizar/criar/atualizar apenas seus próprios customers
- Usuário autenticado pode criar/visualizar/atualizar apenas seus próprios pedidos
- Gerentes/Admin podem confirmar ou rejeitar qualquer pedido em análise
- Nenhum usuário pode alterar ou deletar pedidos já pagos

**Implementação:** Controllers com validação de autorização

**Status:** ⏳ Em desenvolvimento

---

### RN-006: Constraints de Dados

**Descrição:** Define constraints de banco de dados

**Regras:**
- Email é UNIQUE na tabela users
- CNPJ é UNIQUE na tabela customers
- Preços são armazenados como DECIMAL(12,2)
- IDs são UUID v4 (primárias)
- Datas são em UTC com timezone
- Dados históricos nunca são deletados (soft delete quando necessário)

**Implementação:** Anotações JPA, constraints de BD

**Status:** ✅ Implementado

---

## 📊 Matriz de Endpoints

| Método | Endpoint | RF | Status |
|--------|----------|-----|--------|
| POST | `/users` | RF-001 | ✅ |
| GET | `/users/{id}` | RF-002 | ✅ |
| PUT | `/users/{id}` | RF-003 | ✅ |
| DELETE | `/users/{id}` | RF-004 | ✅ |
| POST | `/customers` | RF-005 | ⏳ |
| GET | `/customers/{id}` | RF-005 | ⏳ |
| POST | `/orders` | RF-006 | ⏳ |
| GET | `/orders/{id}` | RF-006 | ⏳ |
| PATCH | `/orders/{id}/confirm` | RF-007 | ⏳ |
| PATCH | `/orders/{id}/pay` | RF-008 | ⏳ |
| PATCH | `/orders/{id}/cancel` | - | ⏳ |

---

*Documento criado em: 2026-04-06*  
*Última atualização: 2026-04-06*
