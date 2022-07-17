package hu.beni.tax;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import hu.beni.tax.entity.Article;
import hu.beni.tax.filter.TaxFilter;
import hu.beni.tax.repository.ArticleRepository;

@SpringBootApplication
public class TaxApplication {

	private static final List<Article> ARTICLES = List.of(
			Article.builder().title("Tao.: Telephely fogalmának bővülése").date(LocalDate.of(2021, 2, 1))
					.content("Társasági adó telephely fogalma 2021. január 1-jétől két új alponttal egészül ki:<br>"
							+ "<ul><li> új telephelyet keletkeztet a természetes személyen keresztül történő „szolgáltatásnyújtás”, azaz, ha külföldi "
							+ "személy egy adott szolgáltatási tevékenységet munkaviszonyban vagy más jogviszonyban álló természetes "
							+ "személlyel végeztet Magyarországon és amelynek időtartama egybefüggően vagy megszakításokkal bármely 18 hónapos "
							+ "időszakban több mint 183 nap. Az összefüggő és a kapcsolódó szolgáltatásokat az figyelési időtartamon belül össze kell számítani.</li>"
							+ " <li>kettős adóztatás elkerüléséről szóló egyezmény megléte esetén mindig az abban meghatározott telephelyfogalom az irányadó,</li></ul>"
							+ "azaz pontosítja a törvény, hogy az egyezmény telephelyfogalma az irányadó abban az esetben is, ha a Tao tv. telephely-definíciója "
							+ "azt nem tekintené telephelynek. ")
					.build(),
			Article.builder()
					.title("Tao.: Kapcsolt vállalkozással szemben fennálló behajthatatlan követelés kivezetése")
					.date(LocalDate.of(2021, 3, 1))
					.content(
							"Amennyiben a társaságnak kapcsolt vállalkozásával szemben Tao tv. szerinti behajthatatlan követelése keletkezik, "
									+ "annak összegével – de legfeljebb a korábbi évek elszámolt/nyilvántartott értékvesztés összegével- csökkentheti "
									+ "adózás előtti eredményét, melynek feltétele, hogy a kapcsolt vállalkozásáról és az ügyletet megalapozó gazdasági"
									+ " okokról az éves adóbevalláshoz csatolt „TAOASZ” nevű nyomtatványon adatot szolgáltat. 2021. január 1-jétől az "
									+ "adatszolgáltatási kötelezettség megszűnik, a törvény nyilvántartási kötelezettséget ír elő, azaz elegendő, ha a "
									+ "társaság vezeti nyilvántartását az ügyletet megalapozó, valós gazdasági okokról. ")
					.build(),
			Article.builder().title("Art.: Késedelmi pótlék számítása").date(LocalDate.of(2021, 5, 1)).content(
					"Késedelmi pótlék mértéke minden naptári nap után a késedelem, illetve az esedékesség előtti igénybevétel(felszámítás) "
							+ "időpontjában érvényes jegybanki alapkamat 5%-al növelt mértékének 365-d része. A késedelmi pótlék után késedelmi pótlékot "
							+ "felszámítani nem lehet. Ezt a mértéket azonban csak 2019. január 1-jét követően esedékessé vált kötelezettségekre kell "
							+ "alkalmazni.<br> "
							+ "Amennyiben tehát a késedelmes kötelezettség kezdő időpontja 2019. január1-jénél korábbi – függetlenül a záró időpontban megadott"
							+ " dátumtól – úgy az adott összegre felszámított pótlék megállapításánál a jegybanki alapkamat kétszeres mértékével kell számolni. "
							+ "Tételezzük fel, hogy a társaság 2017. évre fejlesztési tartalékképzéssel csökkentette adózás előtti eredményét, melyet a lekötés "
							+ "adóévét követő 4 éven belül-legkésőbb 2021. december 31-ig- kellene beruházás bekerülési értékének megfelelő összegben feloldania. "
							+ "Ha a képzett fejlesztési tartaléknak nem a teljes összegében valósul meg beruházás, a társaságnak a fel nem oldott összegre jutó "
							+ "társasági adót utólag kell megfizetnie és annak késedelmes teljesítésére késedelmi pótlékot kell felszámítania. A 2017. évi 1729-es "
							+ "társasági adóbevallás benyújtási határidejét követő naptól, azaz 2018. június 1-től kell felszámítania a meg nem fizetett társasági"
							+ " adóra jutó késedelmi pótlék 2018. december 31-ig hatályos mértékét, azaz a jegybanki alapkamat kétszeresének megfelelő összeget. "
							+ "Késedelmi pótlékszámítás záró napjától számított 30 napon belül kell az adóhatóságnál vezetett számlaszámra megfizetnie és a "
							+ "2129-es társasági adóbevallásában bevallania. ")
					.build(),
			Article.builder()
					.title("PM közlemény: Beszámoló és éves bevallások késedelmes teljesítése 2021. június 31-ig")
					.date(LocalDate.of(2021, 6, 1))
					.content(
							"A számviteli törvény szerinti beszámoló és az arra épülő bevallások 2021. május 31-i benyújtási határidejét a koronavírus-járvány "
									+ "okozta nehézségek miatt késedelembe esett adózók 2021. június 30-ig meghosszabbíthatják. A mulasztás következményei alól akkor "
									+ "mentesülnek, ha a benyújtással egyidejűleg igazolási kérelmet terjesztenek elő, melyben kérni kell az adó megfizetési késedelemre"
									+ " automatikusan felszámításra kerülő késedelmi pótlék alóli mentesülést is. Kérelemnek nincs előírt formája, de az adóhivatal "
									+ "közzétett egy segédletet, melyet az érintettek e_papíron nyújthatnak be. Késedelem miatt az adózót törlik a köztartozásmentes "
									+ "adatbázisból (KOMA), de ha kimentési kérelmét az adóhatóság elfogadja a nyilvántartásba is visszahelyezi.")
					.build(),
			Article.builder().title(
					"Sztv.: Elévülés miatt leírható-e, mint tulajdonossal szemben elévült kötelezettség, az öt éven túli tartozásból"
							+ " fennálló tagi kölcsön?")
					.date(LocalDate.of(2021, 7, 1))
					.content(
							"A beszámoló mérlegébe csak elismert tartozást lehet beállítani és mivel a beszámolót a tulajdonosoknak - általános esetben a "
									+ "fordulónapot követő 150 napon belül - el kell fogadniuk, azt évente elismerik, így az elismerés mindig megszakítja az elévülést."
									+ " Tulajdonostól kapott tagi kölcsön sosem évül el.<br>"
									+ "Amennyiben a tulajdonos követelését elengedi, az a társaságnál vagyonosodást – nem kell visszafizetni, egyéb bevétel lesz "
									+ "– eredményez és az illetéktörvény nem ad illetékmentességet az ügyletre. A társaságot 18%-os illetékfizetési kötelezettség és "
									+ "annak AVBA nyomtatványon bejelentése mellett az egyéb bevételre tekintettel társasági adófizetési kötelezettség is terhelheti.<br>"
									+ "A társaság illetékkötelezettsége átvállalható, de ha azt a tulajdonos magánszemély vállalja át, a társaságnak újabb egyéb "
									+ "bevétele származik, mely után társasági adófizetése keletkezik és ha más forrásból erre nincs fedezet – újabb tagi kölcsön "
									+ "kötelezettség keletkezik és a körforgás ismétlődik.  ")
					.build(),
			Article.builder().title(
					"KIVA: klasszikus átalakulás, pl. bt-ből kft-vé alakulás, azaz társasági formaváltás esetén a jogutód, választása "
							+ "szerint megkaphatja a jogelőd kiva-alanyiságát.")
					.date(LocalDate.of(2021, 8, 1))
					.content(
							"Formaváltás esetén amennyiben a megszűnő jogelőd is kiva-adóalany volt, a jogutód megalakulásával egyidejűleg, azaz cégbejegyzési"
									+ " kérelmének adóhatósághoz benyújtásával választhatja a kiva-alanyiságot. Választása esetén a jogelőd kiva-alanyisága nem "
									+ "szűnik meg, de adózói megszűnésének napját tartalmazó utolsó részidőszakra előlegbevallás helyett a tárgyév első napjától"
									+ " a megszűnése napjáig elszámoló kiva-bevallás benyújtására kötelezett. Jogelőd az átalakulás napjára 90 napon belül számviteli"
									+ " törvény szerinti tevékenységet záró beszámolót készít és közzétesz, mely alapja a jogelőd és jogutód végleges vagyonmérlegeinek"
									+ " és vagyonleltárainak. Amennyiben az átalakulási vagyonmérleg-tervezetek és vagyonleltár-tervezetek nem estek könyvvizsgálati "
									+ "kötelezettség alá, a cégbírósághoz beküldendő végleges vagyonmérlegek és vagyonleltárak könyvvizsgálata minden esetben kötelező.<br>"
									+ "Törvény erejénél fogva az egyesülésnél, a szétválásnál valamennyi résztvevőnek megszűnik a kiva-adóalanyisága, a tovább működőkre"
									+ " és azok jogutódjaira is vonatkozik a 24 hónapos tiltás.")
					.build(),
			Article.builder().title(
					"Tao.: Változás a fejlesztési célú támogatások elszámolásánál 2022. január 01-től, de döntés szerint "
							+ "alkalmazható 2021. évre is.")
					.date(LocalDate.of(2021, 9, 1))
					.content(
							"Elnyert fejlesztési támogatások elszámolásánál is lehetőséget biztosít az évközi törvényváltozás a bevételek aktív időbeli "
									+ "elhatárolására, hasonlóan a költségek ellentételezésére nyújtott, azaz működési célú támogatások 2019.január óta alkalmazható "
									+ "elszámolásához.<br>Aktív időbeli elhatárolásként lehet kimutatni az egyéb bevételekkel szemben a <br>   1.) költségek, "
									+ "ráfordítások ellentételezésére, visszafizetési kötelezettség nélkül <ul><li>természetes személytől, </li>  <li> belföldi "
									+ "vagy külföldi gazdálkodótól vagy külföldi szervezettől kapott, </li> </ul>  2.) költségek, ráfordítások ellentételezésére, "
									+ "visszafizetési kötelezettség nélkül <ul> <li> az adóhatóságtól </li><li>jogszabály által meghatározott szervezettől megkapott, "
									+ "illetve, az <br>üzleti évhez kapcsolódóan a mérlegkészítés időpontjáig jogszabályi előírásoknak megfelelően igényelt, járó "
									+ "támogatás, juttatás összegét, </li></ul> 3.) fejlesztési célra, visszafizetési kötelezettség nélkül kapott, azaz "
									+ "rendeltetésszerűen használatba vett immateriális javak, tárgyi eszközök beszerzéséhez kapcsolódóan, még el nem számolt összegét."
									+ "Az elszámolhatóságra ígérvény mellett van lehetőség, azaz a vállalkozónak bizonyítania kell, hogy a támogatás feltételeit "
									+ "teljesíteni fogja, így jogosult lesz a támogatás kiutalására."
									+ "Az elhatárolt összeget a kapott támogatás elszámolásakor, illetve a támogatás meghiúsulásakor kell megszüntetni.")

					.build(),
			Article.builder()
					.title("SZJA: Gyermeket nevelő szülők adó(előleg)-visszatérítésének kiutalása 2022. február 15-éig")
					.date(LocalDate.of(2021, 11, 1))
					.content("Szja-visszatérítés a családi kedvezményeseknek jár, azaz, aki:<ul>"
							+ "<li>gyermek után szülőként családi pótlékra jogosult,</li>"
							+ "<li>várandós nő és a vele közös háztartásban élő házastárs,</li>"
							+ "<li>a családi pótlékra saját jogán jogosult,</li>"
							+ "<li>rokkantsági járadékban részesül,</li></ul>"
							+ "és akiktől év közben szabályosan vonták le az adóelőlegeket."
							+ "Annak jár a gyermeket nevelő szülők adó-visszatérítése, aki 2021-ben csak egy napra is családi kedvezményre"
							+ " volt(lesz) jogosult. "
							+ "Rendkívüli adó-visszatérítés a gyermekes családoknak akkor jár, ha az adó, az alábbi tevékenységekből származik:"
							+ "<ul><li>az önálló és a nem önálló tevékenység, valamint az egyéb jövedelem adója,</li>"
							+ "<li>az ekho szja részének 2/3-a,</li>"
							+ "<li>a KATA-sok 2021. évi tételes adó 1/4-e</li></ul>"
							+ "Max. visszatéríthető adó az szja és ekhó szerint adózó szülőknek a kedvezmények levonása után fennmaradó része, "
							+ "de legfeljebb külön-külön 809.000, - Ft. KATA tekintetében visszajáró összeggel sem lehet együttesen túllépni a "
							+ "felső határt. "
							+ "<b>Automatikus a visszatérítés </b>annak a szülőnek, akinek a munkabéréből év közben a munkáltató levonja az szja-t, "
							+ "illetve az ekhót."
							+ "<b>„VISSZAADO” nyomtatványon tett nyilatkozat benyújtásával</b> 2021. október 31- december 31-ig a KATA-sok és "
							+ "azok, akiknek a kiutaláshoz szükséges adatait a NAV nem ismerheti."
							+ "<b>21SZJA bevallásukban kérhetik </b>az adó - visszatérítést azok a szülők, akik <ul><li>egyéni vállalkozók,</li>"
							+ "<li>őstermelői jövedelemmel rendelkeznek és akik</li>"
							+ "<li>az összevont adóalapba tartozó jövedelmük után maguk fizetik az szja-t és</li>"
							+ "<li>aki határidőben nem nyilatkozik.</li></ul>" + "Nem jár visszatérítés a"
							+ "<ul><li> külön adózó jövedelmek pl.  lakás eladása, osztalék, tőzsdei nyereség utáni adóból.</li><ul> </ul></ul>")
					.build(),
			Article.builder()
					.title("SZJA-Tao-KIVA: Kifizető által biztosított kerékpár használatának adómentessége 2022.01.01-től.")
					.date(LocalDate.of(2021, 12, 1))
					.content(
							"Az Szja tv. új, 1. számú melléklet 8.44. pontja szerint adómentesen adható a kifizető által biztosított -"
									+ " vásárolt vagy bérelt-, <br> - kizárólag emberi erővel hajtott vagy <br>"
									+ "- legfeljebb 300W teljesítményű, elektromos motorral felszerelt kerékpár magáncélú használata. <br>"
									+ "Függetlenül attól, hogy a magánszemély (munkavállaló vagy társas vállalkozás tagja) a kerékpárt saját személyes"
									+ " céljaira használja vagy sem, adómentes használatnak minősül. Kizárólag a használat, nem pedig a tulajdonba adás adómentes. <br>"
									+ "<b>Tao tv. </b> 3. számú melléklet B) fejezetének 9. pontja a vállalkozás érdekében felmerült költségnek, ráfordításnak minősíti a"
									+ " használaton felül az átadást és ez utóbbi jogviszony szerinti adókötelezettségét. <br>"
									+ "KIVA tv. adóalap növelő tételként a 3. számú melléklet A) fejezetére hivatkozik, így a kerékpár Tao tv. szerint elismert"
									+ " juttatás, ráfordítás nem növeli a KIVA adóalapját.")
					.build(),
			Article.builder()
					.title("SZJA: Mikor adómentesek a koronavírus-járványhoz kapcsolódó egyes juttatások?")
					.date(LocalDate.of(2022, 01, 1))
					.content(
							"Az Szja tv. 1. számú melléklet 8.39. pontja szerint adómentes a kifizető által, a juttatás időpontjától függetlenül biztosított"
									+ " védőoltás, járványügyi szűrővizsgálat. <br> Járványügyi szűrővizsgálatnak az Eütv.59. § (1) bekezdése szerint akkor"
									+ " felel meg a szűrés, ha annak célja <br>"
									+ "- a fertőző megbetegedések korai felismerése, azok <br>"
									+ "- forrásainak felkutatása, valamint <br>"
									+ "- a fertőzés veszélyének elhárítása. <br>"
									+ "Nem tartozik az adómentesen adható járványügyi szűrővizsgálat fogalmába a Covid-ellenanyag kimutatására, illetve a sejtes"
									+ " immunitásra vonatkozó vizsgálat, így ezen juttatás a felek között fennálló jogviszony szerint adóköteles. <br>"
									+ "Adómentes a poszt-Covid-szűrővizsgálat, ha az feltétele a munkába állásnak, azaz közvetlenül kapcsolódik a munkaköri"
									+ " alkalmassághoz, de ha csak lehetőségként biztosítja a munkáltató, akkor a felek között fennálló jogviszony szerint adóköteles. <br>"
									+ "Vészhelyzet ideje alatt adómentesen adható a munkáltató által a munkavállalóknak biztosított, az előzőekben már említett,"
									+ " ellenanyag-vizsgálat és poszt-Covid-szűrővizsgálat a Stab. tv. szerint, mint a vészhelyzet következményeinek elhárítását,"
									+ " mérséklését eredményező juttatás, ha azt bejelentik a NAV-hoz. ")
					.build(),
			Article.builder()
					.title("HIPA: Kell-e ismételten 22NYHIPA nyilatkozatot tenni február 25-ig a csökkentett összegű iparűzési adóelőleg fizetéséhez?"
							+ " Kell-e könyvelni a kedvezmény összegét? ")
					.date(LocalDate.of(2022, 02, 1))
					.content(
							"22NYHIPA nyilatkozat lehetőséget ad a vállalkozóknak, hogy az önkormányzati adómértékkel számított iparűzési adóelőlegnek csak"
									+ " a felét fizessék, amennyiben arra jogosultak. <br>"
									+ "Nyilatkozatot annak kell benyújtania, aki <br>"
									+ "- 2021. december 31-ig nem adott 21NYHIPA nyilatkozatot, vagy <br>"
									+ "- adott ugyan nyilatkozatot, de annak megtételét követően új telephelyet létesített vagy megváltoztatta székhelyét, vagy <br>"
									+ "- 2021-re tett nyilatkozatát, hogy az adóelőnyt de minimis támogatásnak tekinti, kívánja megváltoztatni átmeneti"
									+ " támogatásként való igénybevételre vagy fordítva. <br>"
									+ "Nem kell nyilatkoznia a KATA alanynak, amely az iparűzési adóalap egyszerűsített meghatározását választotta, mert adóelőleg"
									+ " helyett, I.félévre jutó adót fizet. Az éves 50EFt adót az önkormányzat hivatalból 25EFt-ra csökkenti. <br>"
									+ "Kötelezettségként a csökkentett adóévre jutó adót kell könyvelni. A kedvezmény adórendszeren belüli támogatásnak minősül,"
									+ " azt nem könyveljük, de nyilvántartjuk.")
					.build(),
			Article.builder()
					.title("Kkv. tv. Természetes személy vagy azok csoportja révén fennálló kapcsolat a Kkv.tv. alapján")
					.date(LocalDate.of(2022, 3, 1))
					.content(
							"A Kkv. tv. 4. § (5) bekezdése értelmében a vállalkozások között fennálló kapcsolódó vállalkozási "
									+ "jogviszonyt az is megalapozza, ha az egy természetes személy vagy közösen fellépő természetes "
									+ "személyek egy csoportja révén jön létre, amennyiben a vállalkozások a tevékenységüket vagy annak "
									+ "egy részét az érintett piacon vagy egymással szomszédos piacokon folytatják. Kapcsolat lehet "
									+ "többségi tulajdonlás, döntő irányítási vagy ellenőrzési jogkör. A közösen fellépő természetes "
									+ "személyek csoportja úgy határozható meg, hogy a társaságoknak olyan magánszemély tulajdonosai "
									+ "vannak, akik üzleti érdekeiket összehangoltan érvényesítik, valamilyen célból közösen cselekednek. "
									+ "Ennek értelmében egymástól független természetes személyek által tulajdonolt vállalkozások esetén is "
									+ "fennállhat kapcsolódó vállalkozási viszony. Minden esetben az egyedi tényállás alapján lehet "
									+ "meghatározni, hogy a körülmények alapján a közös gazdasági érdek kikövetkeztethető-e.")
					.build(),
			Article.builder().title(
					"Sztv.: Szerződés elszámolási egysége és a teljesítési fok meghatározása nem számlázott alvállalkozói teljesítés esetén")
					.date(LocalDate.of(2022, 4, 1))
					.content(
							"Az összemérés elvével összhangban el kell számolni az eredményben a szerződés elszámolási egységéhez kapcsolódó,"
									+ " az adott teljesítési fokhoz felmerült költségeket, ráfordításokat. Tárgyévi árbevételnek kell fedezetet"
									+ " nyújtania az adott évet terhelő költségekre. A teljesítési fok, azaz a ténylegesen elvégzett munkáknak"
									+ "az elvégzendő összes munkához viszonyított arányát, befolyásolja az alvállalkozó szerződés szerinti teljesítése,"
									+ " azaz, hogy az elvégzett munkát mikor fogadják el. Ha a munkát az alvállalkozó elvégezte ugyan a fordulónapig,"
									+ " de a teljesítés igazolás szerint tőle csak a következő évben vették azt át, így számlázni is csak a következő"
									+ " évben jogosult, teljesítési fok meghatározásánál a fordulónapot követően elismert és számlázott összeget"
									+ " figyelembe venni nem lehet.<br>Az új előírások szerint az - áfa-t nem tartalmazó árbevételt a szerződés elszámolási"
									+ " egysége teljesítésével arányosan (a teljesítési fok arányában) kell elszámolni az eredményben. <br>"
									+ "2019.évben vagy azt megelőzően kötött és 2020. évben még be nem fejeződött projekteknél az adózót választási"
									+ " lehetőség illeti a fordulónapi elszámolásnál, hogy <br> "
									+ "- a „hagyományos” módon, befejezetlen termelésként mutatja be, vagy <br>"
									+ "- a projektszámvitel alkalmazását választja, ha a teljesítmény, szerződés elszámolási egységhez tartozik. <br>"
									+ "Magyar Közlöny 2021. évi 106. számában megjelent módosítás újabb változást hozott, mely	szerint az adózó döntésére"
									+ " bízza, hogy alkalmazza-e ezt az elszámolási módot sorozatgyártás esetén nagytömegű termékrendeléseknél.")
					.build(),
			Article.builder().title("Tao.: Fejlesztési tartalék, mint az előrehozott értékcsökkenés új korlátja")
					.date(LocalDate.of(2022, 5, 3))
					.content(
							"Az előrehozott értékcsökkenés célja, hogy a négy éven belül megkezdett beruházás megvalósítására legyen "
									+ "elegendő forrás.<br>Tao tv. 7. § (1) bekezdés f) pontja szerint az adózás előtti eredményt csökkenti az "
									+ "eredménytartaléknak az adóévben lekötött tartalékba átvezetett és az adóév utolsó napján a beszámolóban "
									+ "lekötött tartalékként (a továbbiakban: fejlesztési tartalék) kimutatott összege. 2020. évközi törvénymódosítás "
									+ "megváltoztatta a korábbi 50%-os korlátot, hatályos jogszabály szerint a korlát az adóévi adózás előtti nyereség, "
									+ "de legfeljebb adóévenként 10 milliárd forint. Tao tv. 7. § (15) bekezdése felsorolja, hogy mely beruházásoknál "
									+ "nem élhet az adózó az előrehozott értékcsökkenéssel, egyben túlmutat a számviteli törvény szerinti értékcsökkenési "
									+ "korlátokra. Megvásárolni kívánt telek bekerülési értékére, amennyiben nem tartozik a kivételek közé, fejlesztési "
									+ "tartalékot feloldani nem szabad, annak értékcsökkenését tiltja a törvény.<br>Ha a fejlesztési tartalék képzésénél, "
									+ "annak felhasználásával összefüggésben hibák fordulnak elő, annak a következménye lehet adóhiány, adóbírság, "
									+ "mulasztási bírság. Immateriális javak bekerülési értéke alapján jogkövetkezmények nélkül nem oldható fel a tartalék."
									+ "NAV 2022.05.03-án közzétett 2022/2. Adózási kérdésben megerősíti, hogy a fejlesztési tartalék nem használható fel "
									+ "felújításra, kizárólag beruházásra.")
					.build(),
			Article.builder()
					.title("Sztv.: Ingatlan bekerülési értékének meghatározása egyösszegű adásvételi szerződés esetén")
					.date(LocalDate.of(2022, 6, 1))
					.content(
							"Ingatlanvásárlás számviteli szempontból minden esetben telekből és felépítményből áll, tehát még abban az"
									+ " esetben is meg kell bontani az eszköz szerződés szerinti egyösszegű vételárát, ha az ügyvéd azt nem követelte"
									+ " meg a szerződő felektől. Tekintettel arra, hogy főszabály szerint a számviteli törvény a befektetett eszközök"
									+ " között nyilvántartott telekre tiltja az értékcsökkenés elszámolását, jogalkotó szándékával ellentételes, ha az"
									+ " ingatlan egyösszegű – telek és felépítmény – aktiválását követően a teljes szerződés szerinti vételárra "
									+ "értékcsökkenést számolnak el.  ")
					.build(),
			Article.builder().title("Sztv.: Jóváhagyott osztalék elengedésének számviteli elszámolása")
					.date(LocalDate.of(2022, 7, 9))
					.content(
							"2020. november 27-től megváltozik a jóváhagyott, de a tulajdonos részére még ki nem fizetett osztalék elengedésének"
									+ " könyvelése az osztalékot jóváhagyó társaságnál. Törvénymódosítást megelőzően az elengedett kötelezettséget az egyéb"
									+ " bevételekkel szemben kellett kivezetni, törvénymódosítás hatálybalépésének napjától az eredménytartalékba vezetésével"
									+ " kell megszüntetni. Módosítást követően értelmezhetetlenné válik a korábbi adózás előtti eredmény csökkentése az "
									+ "elengedett összegre tekintettel, hiszen már nem jelenik meg egyéb bevételként, visszakerül eredeti helyére az "
									+ "eredménytartalékba. <br>"
									+ "Amennyiben az elengedett kötelezettség eredeti jogosultja kapcsolt vállalkozása, annál, azaz a tulajdonosnál "
									+ "osztalékkövetelés elengedésére tekintettel kivezetett egyéb ráfordítás adózás előtti eredményt növelő tétel.")
					.build());

	public static void main(String[] args) {
		SpringApplication.run(TaxApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(JavaMailSender mailSender, ArticleRepository articleRepository) {
		return args -> {

			articleRepository.deleteAll();
			articleRepository.saveAll(ARTICLES);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("nembence1994@gmail.com");
			message.setTo("nembence1994@gmail.com");
			message.setSubject("Check email sending.");
			message.setText("Check email sending.");
			mailSender.send(message);

		};
	}

	@Bean
	public FilterRegistrationBean<TaxFilter> loggingFilter() {
		FilterRegistrationBean<TaxFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TaxFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
