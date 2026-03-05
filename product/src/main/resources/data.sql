-- 20 produtos genéricos
INSERT INTO products (product_id, name, price, description, image_url, type, is_available)
VALUES
    (gen_random_uuid(), 'Camiseta Azul', 59.90, 'Camiseta básica azul', 'img/camiseta_azul.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Camiseta Preta', 49.90, 'Camiseta básica preta', 'img/camiseta_preta.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Geek', 29.90, 'Caneca temática geek', 'img/caneca_geek.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Caneca Star Wars', 39.90, 'Caneca do Star Wars', 'img/caneca_starwars.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Branca', 55.00, 'Camiseta básica branca', 'img/camiseta_branca.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Harry Potter', 35.00, 'Caneca temática HP', 'img/caneca_hp.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Vermelha', 60.00, 'Camiseta básica vermelha', 'img/camiseta_vermelha.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Marvel', 32.00, 'Caneca dos Vingadores', 'img/caneca_marvel.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Verde', 58.00, 'Camiseta básica verde', 'img/camiseta_verde.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca DC Comics', 30.00, 'Caneca da DC', 'img/caneca_dc.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Cinza', 52.00, 'Camiseta básica cinza', 'img/camiseta_cinza.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Game of Thrones', 40.00, 'Caneca GOT', 'img/caneca_got.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Amarela', 57.00, 'Camiseta básica amarela', 'img/camiseta_amarela.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Senhor dos Anéis', 42.00, 'Caneca LOTR', 'img/caneca_lotr.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Roxa', 61.00, 'Camiseta básica roxa', 'img/camiseta_roxa.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Matrix', 33.00, 'Caneca Matrix', 'img/caneca_matrix.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Laranja', 54.00, 'Camiseta básica laranja', 'img/camiseta_laranja.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Naruto', 36.00, 'Caneca Naruto', 'img/caneca_naruto.png', 'CANECA', TRUE),
    (gen_random_uuid(), 'Camiseta Rosa', 59.00, 'Camiseta básica rosa', 'img/camiseta_rosa.png', 'CAMISETA', TRUE),
    (gen_random_uuid(), 'Caneca Dragon Ball', 38.00, 'Caneca Dragon Ball', 'img/caneca_db.png', 'CANECA', TRUE);

-- 5 autores
INSERT INTO authors (author_id, name)
VALUES
    (gen_random_uuid(), 'J. K. Rowling'),
    (gen_random_uuid(), 'George R. R. Martin'),
    (gen_random_uuid(), 'J. R. R. Tolkien'),
    (gen_random_uuid(), 'Stephen King'),
    (gen_random_uuid(), 'Isaac Asimov');

-- 20 livros (cada um também precisa de um registro em products)
WITH new_books AS (
INSERT INTO products (product_id, name, price, description, image_url, type, is_available)
VALUES
    (gen_random_uuid(), 'Harry Potter e a Pedra Filosofal', 79.90, 'Primeiro livro da saga HP', 'img/hp1.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Harry Potter e a Câmara Secreta', 82.90, 'Segundo livro da saga HP', 'img/hp2.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Game of Thrones', 99.90, 'Primeiro livro da saga GOT', 'img/got1.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'A Clash of Kings', 95.90, 'Segundo livro da saga GOT', 'img/got2.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'O Senhor dos Anéis - A Sociedade do Anel', 89.90, 'Primeiro livro da saga LOTR', 'img/lotr1.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'O Senhor dos Anéis - As Duas Torres', 92.90, 'Segundo livro da saga LOTR', 'img/lotr2.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'O Senhor dos Anéis - O Retorno do Rei', 94.90, 'Terceiro livro da saga LOTR', 'img/lotr3.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'It - A Coisa', 85.90, 'Clássico de terror', 'img/it.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'O Iluminado', 83.90, 'Clássico de Stephen King', 'img/shining.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Fundação', 88.90, 'Primeiro livro da saga Fundação', 'img/fundacao.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Fundação e Império', 90.90, 'Segundo livro da saga Fundação', 'img/fundacao2.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Segunda Fundação', 91.90, 'Terceiro livro da saga Fundação', 'img/fundacao3.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Carrie', 79.90, 'Primeiro livro de Stephen King', 'img/carrie.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Misery', 82.90, 'Clássico de suspense', 'img/misery.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'A Dança da Morte', 95.90, 'Livro épico de King', 'img/danca.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'O Hobbit', 87.90, 'Prelúdio de LOTR', 'img/hobbit.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'A Guerra dos Tronos - Prequel', 93.90, 'História anterior a GOT', 'img/got_prequel.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Harry Potter e o Prisioneiro de Azkaban', 84.90, 'Terceiro livro da saga HP', 'img/hp3.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Harry Potter e o Cálice de Fogo', 96.90, 'Quarto livro da saga HP', 'img/hp4.png', 'LIVRO', TRUE),
    (gen_random_uuid(), 'Harry Potter e a Ordem da Fênix', 98.90, 'Quinto livro da saga HP', 'img/hp5.png', 'LIVRO', TRUE)
    RETURNING product_id, name
    )
INSERT INTO books (product_id, quantity_pages, category, publish)
SELECT product_id,
       350 + (random()*200)::int, -- páginas aleatórias
    'FICTION',
       'Editora Exemplo'
FROM new_books;
