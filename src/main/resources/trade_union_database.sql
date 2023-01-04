PGDMP         '    
             {         
   tradeUnion    15.1    15.1 \    [           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            \           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ]           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ^           1262    16563 
   tradeUnion    DATABASE     �   CREATE DATABASE "tradeUnion" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "tradeUnion";
                postgres    false            �            1255    16607    trigger_updatable()    FUNCTION     �   CREATE FUNCTION public.trigger_updatable() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    new.updated := CURRENT_TIMESTAMP;
RETURN new;
END
$$;
 *   DROP FUNCTION public.trigger_updatable();
       public          postgres    false            �            1259    16564    class_education_seq    SEQUENCE     ~   CREATE SEQUENCE public.class_education_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.class_education_seq;
       public          postgres    false            �            1259    16569    class_education    TABLE       CREATE TABLE public.class_education (
    id integer DEFAULT nextval('public.class_education_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    name1 character varying(255),
    name2 character varying(50),
    updated timestamp(6) without time zone
);
 #   DROP TABLE public.class_education;
       public         heap    postgres    false    214            �            1259    16673    class_marital_state    TABLE     �   CREATE TABLE public.class_marital_state (
    id integer NOT NULL,
    name1 character varying(255),
    name2 character varying(255),
    name3 character varying(255),
    updated timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
 '   DROP TABLE public.class_marital_state;
       public         heap    postgres    false            �            1259    16672    class_marital_state_id_seq    SEQUENCE     �   CREATE SEQUENCE public.class_marital_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.class_marital_state_id_seq;
       public          postgres    false    228            _           0    0    class_marital_state_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.class_marital_state_id_seq OWNED BY public.class_marital_state.id;
          public          postgres    false    227            �            1259    16575 	   class_org    TABLE     �   CREATE TABLE public.class_org (
    id integer NOT NULL,
    name character varying(255),
    city character varying(255),
    address character varying(255),
    updated timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
    DROP TABLE public.class_org;
       public         heap    postgres    false            `           0    0    COLUMN class_org.name    COMMENT     ?   COMMENT ON COLUMN public.class_org.name IS 'Название';
          public          postgres    false    220            a           0    0    COLUMN class_org.city    COMMENT     9   COMMENT ON COLUMN public.class_org.city IS 'Город';
          public          postgres    false    220            b           0    0    COLUMN class_org.address    COMMENT     <   COMMENT ON COLUMN public.class_org.address IS 'Адрес';
          public          postgres    false    220            �            1259    16565 
   job_id_seq    SEQUENCE     s   CREATE SEQUENCE public.job_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.job_id_seq;
       public          postgres    false            �            1259    16581    doc_job    TABLE     Z  CREATE TABLE public.doc_job (
    person_id integer NOT NULL,
    place character varying(255) NOT NULL,
    post character varying(255) NOT NULL,
    created date NOT NULL,
    finished date,
    updated timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id integer DEFAULT nextval('public.job_id_seq'::regclass) NOT NULL
);
    DROP TABLE public.doc_job;
       public         heap    postgres    false    215            c           0    0    COLUMN doc_job.place    COMMENT     }   COMMENT ON COLUMN public.doc_job.place IS 'Назва прадпрыемства, установы, арганізацыі';
          public          postgres    false    221            d           0    0    COLUMN doc_job.post    COMMENT     L   COMMENT ON COLUMN public.doc_job.post IS 'Займаемая пасада';
          public          postgres    false    221            �            1259    16566    member_id_seq    SEQUENCE     v   CREATE SEQUENCE public.member_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.member_id_seq;
       public          postgres    false            �            1259    16588 
   doc_member    TABLE     �  CREATE TABLE public.doc_member (
    num integer DEFAULT nextval('public.member_id_seq'::regclass) NOT NULL,
    created date NOT NULL,
    finished date,
    completed date NOT NULL,
    person_id integer NOT NULL,
    org_id integer NOT NULL,
    updated timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP,
    id integer DEFAULT nextval('public.member_id_seq'::regclass) NOT NULL
);
    DROP TABLE public.doc_member;
       public         heap    postgres    false    216    216            e           0    0    COLUMN doc_member.num    COMMENT     L   COMMENT ON COLUMN public.doc_member.num IS 'Членскі білет №';
          public          postgres    false    222            f           0    0    COLUMN doc_member.created    COMMENT     d   COMMENT ON COLUMN public.doc_member.created IS 'Дата уступлення ў прафсаюз';
          public          postgres    false    222            g           0    0    COLUMN doc_member.finished    COMMENT     _   COMMENT ON COLUMN public.doc_member.finished IS 'Дата выхада з прафсаюза';
          public          postgres    false    222            h           0    0    COLUMN doc_member.completed    COMMENT     R   COMMENT ON COLUMN public.doc_member.completed IS 'Дата заполнения';
          public          postgres    false    222            i           0    0    COLUMN doc_member.person_id    COMMENT     =   COMMENT ON COLUMN public.doc_member.person_id IS 'Член';
          public          postgres    false    222            j           0    0    COLUMN doc_member.org_id    COMMENT     ^   COMMENT ON COLUMN public.doc_member.org_id IS 'Прафсаюза і арганізацыі';
          public          postgres    false    222            �            1259    16567    payment_id_seq    SEQUENCE     w   CREATE SEQUENCE public.payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.payment_id_seq;
       public          postgres    false            �            1259    16594    doc_payment    TABLE     *  CREATE TABLE public.doc_payment (
    person_id integer NOT NULL,
    created date NOT NULL,
    finished date,
    org_id integer NOT NULL,
    updated timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id integer DEFAULT nextval('public.payment_id_seq'::regclass) NOT NULL
);
    DROP TABLE public.doc_payment;
       public         heap    postgres    false    217            k           0    0    COLUMN doc_payment.person_id    COMMENT     >   COMMENT ON COLUMN public.doc_payment.person_id IS 'Член';
          public          postgres    false    223            l           0    0    COLUMN doc_payment.created    COMMENT     O   COMMENT ON COLUMN public.doc_payment.created IS 'Начало периода';
          public          postgres    false    223            m           0    0    COLUMN doc_payment.finished    COMMENT     N   COMMENT ON COLUMN public.doc_payment.finished IS 'Конец периода';
          public          postgres    false    223            n           0    0    COLUMN doc_payment.org_id    COMMENT     ]   COMMENT ON COLUMN public.doc_payment.org_id IS 'Прафсаюз і арганізацыя';
          public          postgres    false    223            �            1259    16568    person_id_seq    SEQUENCE     v   CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public          postgres    false            �            1259    16599    person    TABLE     �  CREATE TABLE public.person (
    fn character varying(255) NOT NULL,
    ln character varying(255) NOT NULL,
    mn character varying(255),
    birth date NOT NULL,
    education character varying(255),
    address character varying(255),
    phone character varying(255),
    id integer DEFAULT nextval('public.person_id_seq'::regclass) NOT NULL,
    birth_place character varying(255),
    live_place character varying(255),
    reg_place character varying(255),
    marital_id smallint DEFAULT 0 NOT NULL,
    citizenship character varying(100),
    nationality character varying(100),
    comment character varying(255),
    updated timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
    DROP TABLE public.person;
       public         heap    postgres    false    218            o           0    0    COLUMN person.fn    COMMENT     0   COMMENT ON COLUMN public.person.fn IS 'Імя';
          public          postgres    false    224            p           0    0    COLUMN person.ln    COMMENT     <   COMMENT ON COLUMN public.person.ln IS 'Прозвішча';
          public          postgres    false    224            q           0    0    COLUMN person.mn    COMMENT     B   COMMENT ON COLUMN public.person.mn IS 'Імя па бацьку';
          public          postgres    false    224            r           0    0    COLUMN person.birth    COMMENT     H   COMMENT ON COLUMN public.person.birth IS 'Дата нараджэня';
          public          postgres    false    224            s           0    0    COLUMN person.education    COMMENT     A   COMMENT ON COLUMN public.person.education IS 'Адукацыя';
          public          postgres    false    224            t           0    0    COLUMN person.address    COMMENT     H   COMMENT ON COLUMN public.person.address IS 'Дамашні адрас';
          public          postgres    false    224            u           0    0    COLUMN person.phone    COMMENT     A   COMMENT ON COLUMN public.person.phone IS '№ тэлефона';
          public          postgres    false    224            v           0    0    COLUMN person.birth_place    COMMENT     N   COMMENT ON COLUMN public.person.birth_place IS 'Место рождения';
          public          postgres    false    224            w           0    0    COLUMN person.live_place    COMMENT     j   COMMENT ON COLUMN public.person.live_place IS 'Место фактического жительства';
          public          postgres    false    224            x           0    0    COLUMN person.reg_place    COMMENT     R   COMMENT ON COLUMN public.person.reg_place IS 'Место регистрации';
          public          postgres    false    224            y           0    0    COLUMN person.marital_id    COMMENT     U   COMMENT ON COLUMN public.person.marital_id IS 'Семейное положение';
          public          postgres    false    224            z           0    0    COLUMN person.citizenship    COMMENT     I   COMMENT ON COLUMN public.person.citizenship IS 'Гражданство';
          public          postgres    false    224            {           0    0    COLUMN person.nationality    COMMENT     O   COMMENT ON COLUMN public.person.nationality IS 'Национальность';
          public          postgres    false    224            |           0    0    COLUMN person.comment    COMMENT     E   COMMENT ON COLUMN public.person.comment IS 'Комментарий';
          public          postgres    false    224            �            1259    16650    users    TABLE     i  CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    updated timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP,
    created date,
    firstname character varying(255) NOT NULL,
    lastname character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16649    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    226            }           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    225            �           2604    16676    class_marital_state id    DEFAULT     �   ALTER TABLE ONLY public.class_marital_state ALTER COLUMN id SET DEFAULT nextval('public.class_marital_state_id_seq'::regclass);
 E   ALTER TABLE public.class_marital_state ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    16653    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    226    226            O          0    16569    class_education 
   TABLE DATA                 public          postgres    false    219   Xg       X          0    16673    class_marital_state 
   TABLE DATA                 public          postgres    false    228   �h       P          0    16575 	   class_org 
   TABLE DATA                 public          postgres    false    220   �i       Q          0    16581    doc_job 
   TABLE DATA                 public          postgres    false    221   �j       R          0    16588 
   doc_member 
   TABLE DATA                 public          postgres    false    222   �k       S          0    16594    doc_payment 
   TABLE DATA                 public          postgres    false    223   Dl       T          0    16599    person 
   TABLE DATA                 public          postgres    false    224   �l       V          0    16650    users 
   TABLE DATA                 public          postgres    false    226   7s       ~           0    0    class_education_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.class_education_seq', 101, false);
          public          postgres    false    214                       0    0    class_marital_state_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.class_marital_state_id_seq', 4, true);
          public          postgres    false    227            �           0    0 
   job_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.job_id_seq', 11, true);
          public          postgres    false    215            �           0    0    member_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.member_id_seq', 11, true);
          public          postgres    false    216            �           0    0    payment_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.payment_id_seq', 11, true);
          public          postgres    false    217            �           0    0    person_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.person_id_seq', 30, true);
          public          postgres    false    218            �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 37, true);
          public          postgres    false    225            �           2606    16631    person UX_person_name 
   CONSTRAINT     _   ALTER TABLE ONLY public.person
    ADD CONSTRAINT "UX_person_name" UNIQUE (fn, ln, mn, birth);
 A   ALTER TABLE ONLY public.person DROP CONSTRAINT "UX_person_name";
       public            postgres    false    224    224    224    224            �           2606    16610 $   class_education class_education_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.class_education
    ADD CONSTRAINT class_education_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.class_education DROP CONSTRAINT class_education_pkey;
       public            postgres    false    219            �           2606    16681 *   class_marital_state class_marital_state_pk 
   CONSTRAINT     h   ALTER TABLE ONLY public.class_marital_state
    ADD CONSTRAINT class_marital_state_pk PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.class_marital_state DROP CONSTRAINT class_marital_state_pk;
       public            postgres    false    228            �           2606    16613    class_org class_org_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.class_org
    ADD CONSTRAINT class_org_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.class_org DROP CONSTRAINT class_org_pkey;
       public            postgres    false    220            �           2606    16623 #   doc_member membership_card_doc_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.doc_member
    ADD CONSTRAINT membership_card_doc_pkey PRIMARY KEY (id);
 M   ALTER TABLE ONLY public.doc_member DROP CONSTRAINT membership_card_doc_pkey;
       public            postgres    false    222            �           2606    16628    doc_payment payment_doc_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.doc_payment
    ADD CONSTRAINT payment_doc_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.doc_payment DROP CONSTRAINT payment_doc_pkey;
       public            postgres    false    223            �           2606    16633    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    224            �           2606    16657    users users_pk 
   CONSTRAINT     L   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pk;
       public            postgres    false    226            �           2606    16616    doc_job ux_doc_job 
   CONSTRAINT     r   ALTER TABLE ONLY public.doc_job
    ADD CONSTRAINT ux_doc_job UNIQUE (person_id, place, post, created, finished);
 <   ALTER TABLE ONLY public.doc_job DROP CONSTRAINT ux_doc_job;
       public            postgres    false    221    221    221    221    221            �           2606    16621    doc_member ux_doc_member 
   CONSTRAINT     i   ALTER TABLE ONLY public.doc_member
    ADD CONSTRAINT ux_doc_member UNIQUE (person_id, created, org_id);
 B   ALTER TABLE ONLY public.doc_member DROP CONSTRAINT ux_doc_member;
       public            postgres    false    222    222    222            �           2606    16626    doc_payment ux_doc_payment 
   CONSTRAINT     c   ALTER TABLE ONLY public.doc_payment
    ADD CONSTRAINT ux_doc_payment UNIQUE (person_id, created);
 D   ALTER TABLE ONLY public.doc_payment DROP CONSTRAINT ux_doc_payment;
       public            postgres    false    223    223            �           2606    16618    doc_job work_change_doc_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.doc_job
    ADD CONSTRAINT work_change_doc_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.doc_job DROP CONSTRAINT work_change_doc_pkey;
       public            postgres    false    221            �           1259    16682    class_marital_state_id_uindex    INDEX     b   CREATE UNIQUE INDEX class_marital_state_id_uindex ON public.class_marital_state USING btree (id);
 1   DROP INDEX public.class_marital_state_id_uindex;
       public            postgres    false    228            �           1259    16658    users_id_uindex    INDEX     F   CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);
 #   DROP INDEX public.users_id_uindex;
       public            postgres    false    226            �           1259    16663    users_username_uindex    INDEX     R   CREATE UNIQUE INDEX users_username_uindex ON public.users USING btree (username);
 )   DROP INDEX public.users_username_uindex;
       public            postgres    false    226            �           2620    16608    class_education update    TRIGGER     �   CREATE TRIGGER update AFTER INSERT OR UPDATE OF name, name1, name2 ON public.class_education FOR EACH STATEMENT EXECUTE FUNCTION public.trigger_updatable();
 /   DROP TRIGGER update ON public.class_education;
       public          postgres    false    219    229    219    219    219            �           2620    16611    class_org update    TRIGGER     �   CREATE TRIGGER update AFTER INSERT OR UPDATE OF name, city, address ON public.class_org FOR EACH STATEMENT EXECUTE FUNCTION public.trigger_updatable();
 )   DROP TRIGGER update ON public.class_org;
       public          postgres    false    220    229    220    220    220            �           2620    16614    doc_job update    TRIGGER     �   CREATE TRIGGER update AFTER INSERT OR UPDATE OF place, post, person_id, finished, created ON public.doc_job FOR EACH STATEMENT EXECUTE FUNCTION public.trigger_updatable();
 '   DROP TRIGGER update ON public.doc_job;
       public          postgres    false    221    221    221    229    221    221    221            �           2620    16619    doc_member update    TRIGGER     �   CREATE TRIGGER update AFTER INSERT OR UPDATE OF created, finished, completed, num, person_id, org_id ON public.doc_member FOR EACH STATEMENT EXECUTE FUNCTION public.trigger_updatable();
 *   DROP TRIGGER update ON public.doc_member;
       public          postgres    false    222    229    222    222    222    222    222    222            �           2620    16624    doc_payment update    TRIGGER     �   CREATE TRIGGER update AFTER INSERT OR UPDATE OF created, finished, person_id, org_id ON public.doc_payment FOR EACH STATEMENT EXECUTE FUNCTION public.trigger_updatable();
 +   DROP TRIGGER update ON public.doc_payment;
       public          postgres    false    229    223    223    223    223    223            �           2620    16629    person update    TRIGGER       CREATE TRIGGER update AFTER INSERT OR UPDATE OF birth, education, address, phone, birth_place, live_place, fn, reg_place, marital_id, citizenship, nationality, comment, ln, mn ON public.person FOR EACH STATEMENT EXECUTE FUNCTION public.trigger_updatable();
 &   DROP TRIGGER update ON public.person;
       public          postgres    false    224    224    224    224    224    224    224    224    224    224    229    224    224    224    224    224            �           2606    16639 -   doc_member membership_card_doc_person_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_member
    ADD CONSTRAINT membership_card_doc_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) ON UPDATE CASCADE;
 W   ALTER TABLE ONLY public.doc_member DROP CONSTRAINT membership_card_doc_person_id_fkey;
       public          postgres    false    222    224    3243            �           2606    16644 &   doc_payment payment_doc_person_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_payment
    ADD CONSTRAINT payment_doc_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) ON UPDATE CASCADE;
 P   ALTER TABLE ONLY public.doc_payment DROP CONSTRAINT payment_doc_person_id_fkey;
       public          postgres    false    3243    224    223            �           2606    16634 &   doc_job work_change_doc_person_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_job
    ADD CONSTRAINT work_change_doc_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) ON UPDATE CASCADE;
 P   ALTER TABLE ONLY public.doc_job DROP CONSTRAINT work_change_doc_person_id_fkey;
       public          postgres    false    3243    221    224            O   g  x�Ŕ�N�@��<��
	��Z��	�D���-�&�$л-�K��w5�H�f��YK5R�Ƙ�����4�ͷ_�ZoT��Zo}ܱ��������i��:풴efIW?i��>X��=S��9ܫ�*�Ɣw0�W�������[��e4GK9F	��Z�j��iZ�)��T�/�����7����-���ˇ*�g~� /�X��/�G�f�Ñ�	�QtH5�)��_�9�LVDOX(����{B����uPa_P~�K���<�����巹�R�]���t�C�G���N�6,Q����E�������;�Ы�>�X"]�쑻�2|�]C��\�L"�T�lAۋ      X   �   x���v
Q���W((M��L�K�I,.��M,�,Ỉ/.I,IU��L�Q�K�M5�PF�XG�� � ES!��'�5XA�B����.쾰�b��&�4��bz&�4202�5"SC#+c+c3=c3SCSuMk.O�;�d��.��|aۅ��h���6\l�8,Մ8u�@8�d�[�!��� ��bĉ@Q��&��L@6/ ��v���<����- �<.. ���      P   �   x���v
Q���W((M��L�K�I,.��/JW��L�Q�K�M�QH�,��QHLI)J-.�Q(-HI,IM�Ts�	uV�0�QP�����{/6]l�����=@v���
��]l��$�.lW)�saP���.�b��z
f����/l ����Y*ZZ�X���Z���kZsyR�� �L���[@.�$8z�PxP�.���� %��r      Q   �   x���v
Q���W((M��L�K�O���OR�(H-*�ϋ�L�Q(�ILNR��%:
�E��%�@Ѵ̼���� "���������a���~a��6]�wa��v\l���b���@�� ��^��ｰ�bH����P��L������A3�T0��22�2��36�004�1Դ��/�\�~aօE`'o����f��v_l"��F@gsq �c��      R   �   x�M��
�0Fw���T���Ԣ�:8b�jW�Il�Bl޿^�N��p��i�GU����q�,�J�Q8��W����&���وeg�K+�b� W4��8H��V�eARLI���A�����*�F���9���Yq�i�_NtK�k?��2      S   �   x���v
Q���W((M��L�K�O�/H��M�+Q�(H-*�ϋ�L�QH.JM,I2�2�2�3@���t�TiA
D*3ES!��'�5XA�XGA����P��L��X�gl �"	Y*ZZ�[��Y�ZZ��hZsqq Ջ+?      T   J  x��Ko�F����)Db�$W=��C� ���@��Z�_����v�A�&N[ 0l�NQ�TT~�Q�H�
�o���R�c��rX0erw����Ϭnݹ{��{��;�>V=\l�E�����������`�%86�Zՠ1�h��j�@K}~~���^VV�ՠ9oG=X]��A�b��Fq����8]��5[��f�\��������\��8q}���zV��˭j�hu��j��������w��}�=�6t;۪T��~e������>��=���p�����u7���1BhHDH9\ݹ���'K]���S1R2PV��"��Tŕ�\���C������}���A+�Ėߠ�D�i?o�J���!������{���ـ�3���9�}�o�<�
a�N��|�y��� ^g����g��:٦��'���4W)�8����W3
p!?��x6/3S�nAw7{�_����7�:��o�zd?����g���XzRciD�J驱�����O`�<���6��������'���ZSOB�CF���N�������H\N��׳К�5�S!����\����^aUE�q8�%���T�.-|0���]��z�+dN���!8U�f`���F�tp�n��=�q�zP�Ӑ��}p�����K�����Qz�6���T�%�L��r�㡊'&T)��*�ԢFW�=F���"�E�Ƣ8�/ �mB��L-RZn�ӴFxD�H���0U�)N%E�(/�� %��R_�Fn�,>{m�m!!��%NtN{��r%����P1Bj�q#L�y~N�F�>���(q��9��rf���{ZFr$n=aC�3�p�7!��U;'cn���QBm���4V!%����`����7����1����  �&��sʈ$�RFb�|7��P.�aa��u��K�JU#,�,!��� ���6��S.�_h7�.��pq�,L!@	F1%�$��|��$n���5���1�SU�!��E~ V�F�'4�u���y^z����Y�W����p����$ZP8�m\-d�2RIʹ�h��)�z[@Ei�C���m�m�����D�=m��%��2J�8�d�O��`��X�i[��v'{�8����^I�B��`Ui����*�={����v�2�P�@�g�
�A��1ۙ�����&&Q�����:�J��ՔY�Vrm�q�i�Y�I"y^�p`-�VN�D0�m곏6����b�.��hZب��ؙ,k�Y����(�P!1�V����l��pn���M����WV:i�&�"�U��;����!Uݝ�qj���@Zc*��b��'�����ni����.��Y�u V>PX��Ǿ*�� ֽq�v�
�: �]��JR��g@-��$5k�)F���ꊕμ('C��kƥU���T�m�}:�2%�s�Qg�&z��^�mӹ_�~��f��iD8��j#���]��S*Jb��3�T� ����"N���F}� ����ځ������6�#���f��3�wJ��q@Ӛd5B#�S�Z�H=�Y�:��]LۖT���:7-6���S��L���/��[ɶᕞ�h�Xq�R��ڵ� C���      V   �  x�͔�n�@��y
/"%�R{.�AW�;�Mec�&�p[5}�^V]t���VJS%�0�Q�TP%��Y��o��tfJj]�5���Шql��.�NR��}H��3ϝCjl������%>�f�$�8����������f�ؐ��>F�Ԟ�n���]d�B���j�PHK�B����>&��u˶=I�84֘���>p����v,���%5u �&�� �B>��!q��j��gr�|On����M����'�N޷���;x�S�6Y��F~�Fe��2�S��
��I/���x̤P�2�"��"Wx]1/N�V��_*F��m�TJf9�f�`v���pCn�R4�zrO����K����|�7���cDC����2�*-�����^S;)r>﷬<Ė��ja���s��g�|KR���������r�M�
Z��t�s�=XLl_Uf�T�d�+��8mZy]2����EWr'ݪRy��,^ �5��7����x�����W	����`�H[XvAܝCϠz4UN�C�ο|� �
i 	�\#��]z>�ݦ��i����\�q(\QWZ}��Z��ɶ#k��:�9�JN�o[�Sk���{6�f���b:OA1ˊY��l�Ň�MC�"��Ovv� 	{�     