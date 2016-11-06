kontroleri iz ove baze su frontend kontroleri

koji nemaju pristup repozitorijima i "nisu" dio platforme
te za podatke moraju pitati kontrolere iz platforme
(npr ako korisniku treba prikazati popis senzora onda proslijedi upit SensorControler.java)


oni mogu vracati ModelView/Json ili kako vec frontendas zeli za web