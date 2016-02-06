/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     06/02/2016 10:49:46                          */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('AVISO') and o.name = 'FK_AVISO_CURSO_AVI_CURSO')
alter table AVISO
   drop constraint FK_AVISO_CURSO_AVI_CURSO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BUZON') and o.name = 'FK_BUZON_REP_BUZ_REPRESEN')
alter table BUZON
   drop constraint FK_BUZON_REP_BUZ_REPRESEN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CALIFICACION') and o.name = 'FK_CALIFICA_EST_CALIF_ESTUDIAN')
alter table CALIFICACION
   drop constraint FK_CALIFICA_EST_CALIF_ESTUDIAN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CALIFICACION') and o.name = 'FK_CALIFICA_TAREA_CAL_TAREA')
alter table CALIFICACION
   drop constraint FK_CALIFICA_TAREA_CAL_TAREA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CURSO') and o.name = 'FK_CURSO_PROF_CURS_PROFESOR')
alter table CURSO
   drop constraint FK_CURSO_PROF_CURS_PROFESOR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CURSO_REPRESENTANTES') and o.name = 'FK_CURSO_RE_CUR_REP_CURSO')
alter table CURSO_REPRESENTANTES
   drop constraint FK_CURSO_RE_CUR_REP_CURSO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CURSO_REPRESENTANTES') and o.name = 'FK_CURSO_RE_REP_CUR_REPRESEN')
alter table CURSO_REPRESENTANTES
   drop constraint FK_CURSO_RE_REP_CUR_REPRESEN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ESTUDIANTE') and o.name = 'FK_ESTUDIAN_CUR_EST_CURSO')
alter table ESTUDIANTE
   drop constraint FK_ESTUDIAN_CUR_EST_CURSO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ESTUDIANTE') and o.name = 'FK_ESTUDIAN_REP_EST_REPRESEN')
alter table ESTUDIANTE
   drop constraint FK_ESTUDIAN_REP_EST_REPRESEN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('LISTA_AVISOS') and o.name = 'FK_LISTA_AV_AVI_LIS_AVISO')
alter table LISTA_AVISOS
   drop constraint FK_LISTA_AV_AVI_LIS_AVISO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('LISTA_AVISOS') and o.name = 'FK_LISTA_AV_BUZ_LIS_BUZON')
alter table LISTA_AVISOS
   drop constraint FK_LISTA_AV_BUZ_LIS_BUZON
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('MATERIA') and o.name = 'FK_MATERIA_CURSO_MAT_CURSO')
alter table MATERIA
   drop constraint FK_MATERIA_CURSO_MAT_CURSO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TAREA') and o.name = 'FK_TAREA_MAT_TAREA_MATERIA')
alter table TAREA
   drop constraint FK_TAREA_MAT_TAREA_MATERIA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('AVISO')
            and   name  = 'CURSO_AVISO_FK'
            and   indid > 0
            and   indid < 255)
   drop index AVISO.CURSO_AVISO_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('AVISO')
            and   type = 'U')
   drop table AVISO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BUZON')
            and   name  = 'REP_BUZ_FK'
            and   indid > 0
            and   indid < 255)
   drop index BUZON.REP_BUZ_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BUZON')
            and   type = 'U')
   drop table BUZON
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CALIFICACION')
            and   name  = 'TAREA_CALIF_FK'
            and   indid > 0
            and   indid < 255)
   drop index CALIFICACION.TAREA_CALIF_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CALIFICACION')
            and   name  = 'EST_CALIF_FK'
            and   indid > 0
            and   indid < 255)
   drop index CALIFICACION.EST_CALIF_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CALIFICACION')
            and   type = 'U')
   drop table CALIFICACION
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CURSO')
            and   name  = 'PROF_CURSO_FK'
            and   indid > 0
            and   indid < 255)
   drop index CURSO.PROF_CURSO_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CURSO')
            and   type = 'U')
   drop table CURSO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CURSO_REPRESENTANTES')
            and   name  = 'REP_CUR_FK'
            and   indid > 0
            and   indid < 255)
   drop index CURSO_REPRESENTANTES.REP_CUR_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CURSO_REPRESENTANTES')
            and   name  = 'CUR_REP_FK'
            and   indid > 0
            and   indid < 255)
   drop index CURSO_REPRESENTANTES.CUR_REP_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CURSO_REPRESENTANTES')
            and   type = 'U')
   drop table CURSO_REPRESENTANTES
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ESTUDIANTE')
            and   name  = 'CUR_EST_FK'
            and   indid > 0
            and   indid < 255)
   drop index ESTUDIANTE.CUR_EST_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ESTUDIANTE')
            and   name  = 'REP_EST_FK'
            and   indid > 0
            and   indid < 255)
   drop index ESTUDIANTE.REP_EST_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ESTUDIANTE')
            and   type = 'U')
   drop table ESTUDIANTE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('LISTA_AVISOS')
            and   name  = 'BUZ_LIS_FK'
            and   indid > 0
            and   indid < 255)
   drop index LISTA_AVISOS.BUZ_LIS_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('LISTA_AVISOS')
            and   name  = 'AVI_LIS_FK'
            and   indid > 0
            and   indid < 255)
   drop index LISTA_AVISOS.AVI_LIS_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('LISTA_AVISOS')
            and   type = 'U')
   drop table LISTA_AVISOS
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MATERIA')
            and   name  = 'CURSO_MAT_FK'
            and   indid > 0
            and   indid < 255)
   drop index MATERIA.CURSO_MAT_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MATERIA')
            and   type = 'U')
   drop table MATERIA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PROFESOR')
            and   type = 'U')
   drop table PROFESOR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REPRESENTANTE')
            and   type = 'U')
   drop table REPRESENTANTE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('TAREA')
            and   name  = 'MAT_TAREA_FK'
            and   indid > 0
            and   indid < 255)
   drop index TAREA.MAT_TAREA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TAREA')
            and   type = 'U')
   drop table TAREA
go

drop database SISCONE
create database SISCONE
use SISCONE

/*==============================================================*/
/* Table: AVISO                                                 */
/*==============================================================*/
create table AVISO (
   ID_AVISO             int    identity(1,1)              not null,
   ID_CURSO             int                  null,
   TITULO               char(50)             null,
   DESCRIPCION_AVISO    char(500)            null,
   constraint PK_AVISO primary key nonclustered (ID_AVISO)
)
go

/*==============================================================*/
/* Index: CURSO_AVISO_FK                                        */
/*==============================================================*/
create index CURSO_AVISO_FK on AVISO (
ID_CURSO ASC
)
go

/*==============================================================*/
/* Table: BUZON                                                 */
/*==============================================================*/
create table BUZON (
   ID_BUZON             int    identity(1,1)              not null,
   CEDULA_REPRESENTANTE int                  null,
   constraint PK_BUZON primary key nonclustered (ID_BUZON)
)
go

/*==============================================================*/
/* Index: REP_BUZ_FK                                            */
/*==============================================================*/
create index REP_BUZ_FK on BUZON (
CEDULA_REPRESENTANTE ASC
)
go

/*==============================================================*/
/* Table: CALIFICACION                                          */
/*==============================================================*/
create table CALIFICACION (
   ID_CALIFICACION      int                 identity(1,1) not null,
   ID_TAREA             int                  null,
   ID_ESTUDIANTE        int                  null,
   PUNTAJE              int                  null,
   OBSERVACION          char(500)            null,
   constraint PK_CALIFICACION primary key nonclustered (ID_CALIFICACION)
)
go

/*==============================================================*/
/* Index: EST_CALIF_FK                                          */
/*==============================================================*/
create index EST_CALIF_FK on CALIFICACION (
ID_ESTUDIANTE ASC
)
go

/*==============================================================*/
/* Index: TAREA_CALIF_FK                                        */
/*==============================================================*/
create index TAREA_CALIF_FK on CALIFICACION (
ID_TAREA ASC
)
go

/*==============================================================*/
/* Table: CURSO                                                 */
/*==============================================================*/
create table CURSO (
   ID_CURSO             int        identity(1,1)          not null,
   CEDULA_PROFESOR      int                  null,
   NOMBRE_INSTITUCION   char(30)             null,
   NOMBRE_CURSO         char(30)             null,
   PARALELO             char(30)             null,
   constraint PK_CURSO primary key nonclustered (ID_CURSO)
)
go

/*==============================================================*/
/* Index: PROF_CURSO_FK                                         */
/*==============================================================*/
create index PROF_CURSO_FK on CURSO (
CEDULA_PROFESOR ASC
)
go

/*==============================================================*/
/* Table: CURSO_REPRESENTANTES                                  */
/*==============================================================*/
create table CURSO_REPRESENTANTES (
   ID_CURSO_REPRESENTANTES int          identity(1,1)        not null,
   ID_CURSO             int                  null,
   CEDULA_REPRESENTANTE int                  null,
   constraint PK_CURSO_REPRESENTANTES primary key nonclustered (ID_CURSO_REPRESENTANTES)
)
go

/*==============================================================*/
/* Index: CUR_REP_FK                                            */
/*==============================================================*/
create index CUR_REP_FK on CURSO_REPRESENTANTES (
ID_CURSO ASC
)
go

/*==============================================================*/
/* Index: REP_CUR_FK                                            */
/*==============================================================*/
create index REP_CUR_FK on CURSO_REPRESENTANTES (
CEDULA_REPRESENTANTE ASC
)
go

/*==============================================================*/
/* Table: ESTUDIANTE                                            */
/*==============================================================*/
create table ESTUDIANTE (
   ID_ESTUDIANTE        int            identity(1,1)      not null,
   ID_CURSO             int                  null,
   CEDULA_REPRESENTANTE int                  null,
   NOMBRE_ESTUDIANTE    char(50)             null,
   APELLIDO_ESTUDIANTE  char(50)             null,
   constraint PK_ESTUDIANTE primary key nonclustered (ID_ESTUDIANTE)
)
go

/*==============================================================*/
/* Index: REP_EST_FK                                            */
/*==============================================================*/
create index REP_EST_FK on ESTUDIANTE (
CEDULA_REPRESENTANTE ASC
)
go

/*==============================================================*/
/* Index: CUR_EST_FK                                            */
/*==============================================================*/
create index CUR_EST_FK on ESTUDIANTE (
ID_CURSO ASC
)
go

/*==============================================================*/
/* Table: LISTA_AVISOS                                          */
/*==============================================================*/
create table LISTA_AVISOS (
   ID_LISTA_AVISOS      int        identity(1,1)            not null,
   ID_AVISO             int                   null,
   ID_BUZON             int                  null,
   constraint PK_LISTA_AVISOS primary key nonclustered (ID_LISTA_AVISOS)
)
go

/*==============================================================*/
/* Index: AVI_LIS_FK                                            */
/*==============================================================*/
create index AVI_LIS_FK on LISTA_AVISOS (
ID_AVISO ASC
)
go

/*==============================================================*/
/* Index: BUZ_LIS_FK                                            */
/*==============================================================*/
create index BUZ_LIS_FK on LISTA_AVISOS (
ID_BUZON ASC
)
go

/*==============================================================*/
/* Table: MATERIA                                               */
/*==============================================================*/
create table MATERIA (
   ID_MATERIA           int             identity(1,1)     not null,
   ID_CURSO             int                  null,
   NOMBRE_MATERIA       char(50)             null,
   constraint PK_MATERIA primary key nonclustered (ID_MATERIA)
)
go

/*==============================================================*/
/* Index: CURSO_MAT_FK                                          */
/*==============================================================*/
create index CURSO_MAT_FK on MATERIA (
ID_CURSO ASC
)
go

/*==============================================================*/
/* Table: PROFESOR                                              */
/*==============================================================*/
create table PROFESOR (
   CEDULA_PROFESOR      int                  not null,
   NOMBRE_PROFESOR      char(50)             null,
   APELLIDO_PROFESOR    char(50)             null,
   CORREO_PROFESOR      char(50)             null,
   CONTRASENA_PROFESOR  char(50)             null,
   constraint PK_PROFESOR primary key nonclustered (CEDULA_PROFESOR)
)
go

/*==============================================================*/
/* Table: REPRESENTANTE                                         */
/*==============================================================*/
create table REPRESENTANTE (
   CEDULA_REPRESENTANTE int                  not null,
   NOMBRE_REPRESENTANTE char(50)             null,
   APELLIDO_REPRESENTANTE char(50)             null,
   CORREO_REPRESENTANTE char(50)             null,
   CONTRASENA_REPRESENTANTE char(50)             null,
   constraint PK_REPRESENTANTE primary key nonclustered (CEDULA_REPRESENTANTE)
)
go

/*==============================================================*/
/* Table: TAREA                                                 */
/*==============================================================*/
create table TAREA (
   ID_TAREA             int         identity(1,1)         not null,
   ID_MATERIA           int                  null,
   NOMBRE_TAREA         char(50)             null,
   FECHA_TAREA          datetime             null,
   DESCRIPCION_TAREA    char(500)            null,
   constraint PK_TAREA primary key nonclustered (ID_TAREA)
)
go

/*==============================================================*/
/* Index: MAT_TAREA_FK                                          */
/*==============================================================*/
create index MAT_TAREA_FK on TAREA (
ID_MATERIA ASC
)
go

alter table AVISO
   add constraint FK_AVISO_CURSO_AVI_CURSO foreign key (ID_CURSO)
      references CURSO (ID_CURSO)
go

alter table BUZON
   add constraint FK_BUZON_REP_BUZ_REPRESEN foreign key (CEDULA_REPRESENTANTE)
      references REPRESENTANTE (CEDULA_REPRESENTANTE)
go

alter table CALIFICACION
   add constraint FK_CALIFICA_EST_CALIF_ESTUDIAN foreign key (ID_ESTUDIANTE)
      references ESTUDIANTE (ID_ESTUDIANTE)
go

alter table CALIFICACION
   add constraint FK_CALIFICA_TAREA_CAL_TAREA foreign key (ID_TAREA)
      references TAREA (ID_TAREA)
go

alter table CURSO
   add constraint FK_CURSO_PROF_CURS_PROFESOR foreign key (CEDULA_PROFESOR)
      references PROFESOR (CEDULA_PROFESOR)
go

alter table CURSO_REPRESENTANTES
   add constraint FK_CURSO_RE_CUR_REP_CURSO foreign key (ID_CURSO)
      references CURSO (ID_CURSO)
go

alter table CURSO_REPRESENTANTES
   add constraint FK_CURSO_RE_REP_CUR_REPRESEN foreign key (CEDULA_REPRESENTANTE)
      references REPRESENTANTE (CEDULA_REPRESENTANTE)
go

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_CUR_EST_CURSO foreign key (ID_CURSO)
      references CURSO (ID_CURSO)
go

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_REP_EST_REPRESEN foreign key (CEDULA_REPRESENTANTE)
      references REPRESENTANTE (CEDULA_REPRESENTANTE)
go

alter table LISTA_AVISOS
   add constraint FK_LISTA_AV_AVI_LIS_AVISO foreign key (ID_AVISO)
      references AVISO (ID_AVISO)
go

alter table LISTA_AVISOS
   add constraint FK_LISTA_AV_BUZ_LIS_BUZON foreign key (ID_BUZON)
      references BUZON (ID_BUZON)
go

alter table MATERIA
   add constraint FK_MATERIA_CURSO_MAT_CURSO foreign key (ID_CURSO)
      references CURSO (ID_CURSO)
go

alter table TAREA
   add constraint FK_TAREA_MAT_TAREA_MATERIA foreign key (ID_MATERIA)
      references MATERIA (ID_MATERIA)
go

