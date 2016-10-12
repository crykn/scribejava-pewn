# scribejava-pewn
Pewn-API für ScribeJava


Ein kleines Beispielprogramm:
```java
// Die Angaben der Anwendung
String client_id = "12345";
String apiSecret = "ebg12abc-def3-4cc5-ea67-89ba0b998765";

String redirect_url = "egal"; // Der Wert wird von der Pewn-API nicht beachtet
		
//Die Adresse, auf die letztlich zugegriffen werden soll
String PROTECTED_RESOURCE_URL = "https://pewn.de/api/v1/self/details";

// Den Service einrichten
OAuth20Service service = new ServiceBuilder().apiKey(client_id).apiSecret(apiSecret).callback(redirect_url).build(PewnApi.instance());

Scanner in = new Scanner(System.in);

// Die Auth-URL erlangen
System.out.println("Bitte diese Adresse aufrufen:");
System.out.println(service.getAuthorizationUrl());
System.out.println();

// Auth-Code aus Browser ins Program einspeisen
System.out.println("Den Auth-Code hier einfügen:");
System.out.print(">>");
String authCode = in.nextLine();
System.out.println();

// Auth-Code gegen Access-Token tauschen
System.out.println("Auth-Code gegen Access-Token tauschen...");
OAuth2AccessToken accessToken = service.getAccessToken(authCode);
System.out.println("Access-Token erhalten:");
System.out.println(accessToken);
System.out.println();

// Geschütze Ressource aufrufen
System.out.println("Auf geschütze Ressource zugreifen...");
OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
service.signRequest(accessToken, request);
Response response = request.send();
System.out.println();
System.out.println("Die Antwort sieht folgendermaßen aus:");
System.out.println();
System.out.println(response.getCode());
System.out.println(response.getBody());
System.out.println();
		
in.close();
```
