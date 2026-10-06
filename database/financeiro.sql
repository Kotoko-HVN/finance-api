CREATE TABLE IF NOT EXISTS "transacao" (
	"id" serial NOT NULL UNIQUE,
	"descricao" varchar(500) NOT NULL,
	"valor" numeric(12,2) NOT NULL,
	"tipo" varchar(1) NOT NULL,
	"datatransacao" date NOT NULL,
	"conta_id" integer NOT NULL,
	"categoria_id" integer NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE IF NOT EXISTS "categoria" (
	"id" serial NOT NULL UNIQUE,
	"descricao" varchar(150) NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE IF NOT EXISTS "conta" (
	"id" serial NOT NULL UNIQUE,
	"descricao" varchar(150) NOT NULL,
	PRIMARY KEY ("id")
);
ALTER TABLE "transacao" ADD CONSTRAINT "transacao_fk5" FOREIGN KEY ("conta_id") REFERENCES "conta"("id");
ALTER TABLE "transacao" ADD CONSTRAINT "transacao_fk6" FOREIGN KEY ("categoria_id") REFERENCES "categoria"("id");