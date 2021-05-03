DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    player_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL, 
    defense integer NOT NULL,
    attack integer NOT NULL
);

DROP TABLE IF EXISTS public.inventory;
CREATE TABLE public.inventory (
    id serial NOT NULL PRIMARY KEY,
    item_name text NOT NULL,
    player_id integer NOT NULL
);

DROP TABLE IF EXISTS public.monsters;
CREATE TABLE public.monsters (
    id serial NOT NULL PRIMARY KEY,
    monster_name text NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    game_id integer NOT NULL
);

DROP TABLE IF EXISTS public.items;
CREATE TABLE public.items (
    id serial NOT NULL PRIMARY KEY,
    item_name text NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    game_id integer NOT NULL
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);
    
ALTER TABLE ONLY public.monsters
    ADD CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.items
    ADD CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES public.game_state(id);
    
