PGDMP         "                y           customer_web_tracker    13.1    13.2     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    182593    customer_web_tracker    DATABASE     p   CREATE DATABASE customer_web_tracker WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Polish_Poland.1250';
 $   DROP DATABASE customer_web_tracker;
                postgres    false            ?            1259    182594    customer_seq    SEQUENCE     u   CREATE SEQUENCE public.customer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.customer_seq;
       public          postgres    false            ?            1259    182596    customer    TABLE     7  CREATE TABLE public.customer (
    id integer DEFAULT nextval('public.customer_seq'::regclass) NOT NULL,
    first_name character varying(45) DEFAULT NULL::character varying,
    last_name character varying(45) DEFAULT NULL::character varying,
    email character varying(45) DEFAULT NULL::character varying
);
    DROP TABLE public.customer;
       public         heap    postgres    false    200            ?            1259    182627    spring_session    TABLE     6  CREATE TABLE public.spring_session (
    primary_id character(36) NOT NULL,
    session_id character(36) NOT NULL,
    creation_time bigint NOT NULL,
    last_access_time bigint NOT NULL,
    max_inactive_interval integer NOT NULL,
    expiry_time bigint NOT NULL,
    principal_name character varying(100)
);
 "   DROP TABLE public.spring_session;
       public         heap    postgres    false            ?            1259    182635    spring_session_attributes    TABLE     ?   CREATE TABLE public.spring_session_attributes (
    session_primary_id character(36) NOT NULL,
    attribute_name character varying(200) NOT NULL,
    attribute_bytes bytea NOT NULL
);
 -   DROP TABLE public.spring_session_attributes;
       public         heap    postgres    false            ?          0    182596    customer 
   TABLE DATA           D   COPY public.customer (id, first_name, last_name, email) FROM stdin;
    public          postgres    false    201   ?       ?          0    182627    spring_session 
   TABLE DATA           ?   COPY public.spring_session (primary_id, session_id, creation_time, last_access_time, max_inactive_interval, expiry_time, principal_name) FROM stdin;
    public          postgres    false    202          ?          0    182635    spring_session_attributes 
   TABLE DATA           h   COPY public.spring_session_attributes (session_primary_id, attribute_name, attribute_bytes) FROM stdin;
    public          postgres    false    203   0       ?           0    0    customer_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.customer_seq', 6, true);
          public          postgres    false    200            0           2606    182604    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    201            7           2606    182642 6   spring_session_attributes spring_session_attributes_pk 
   CONSTRAINT     ?   ALTER TABLE ONLY public.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name);
 `   ALTER TABLE ONLY public.spring_session_attributes DROP CONSTRAINT spring_session_attributes_pk;
       public            postgres    false    203    203            5           2606    182631     spring_session spring_session_pk 
   CONSTRAINT     f   ALTER TABLE ONLY public.spring_session
    ADD CONSTRAINT spring_session_pk PRIMARY KEY (primary_id);
 J   ALTER TABLE ONLY public.spring_session DROP CONSTRAINT spring_session_pk;
       public            postgres    false    202            1           1259    182632    spring_session_ix1    INDEX     Z   CREATE UNIQUE INDEX spring_session_ix1 ON public.spring_session USING btree (session_id);
 &   DROP INDEX public.spring_session_ix1;
       public            postgres    false    202            2           1259    182633    spring_session_ix2    INDEX     T   CREATE INDEX spring_session_ix2 ON public.spring_session USING btree (expiry_time);
 &   DROP INDEX public.spring_session_ix2;
       public            postgres    false    202            3           1259    182634    spring_session_ix3    INDEX     W   CREATE INDEX spring_session_ix3 ON public.spring_session USING btree (principal_name);
 &   DROP INDEX public.spring_session_ix3;
       public            postgres    false    202            8           2606    182643 6   spring_session_attributes spring_session_attributes_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES public.spring_session(primary_id) ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.spring_session_attributes DROP CONSTRAINT spring_session_attributes_fk;
       public          postgres    false    2869    203    202            ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?     