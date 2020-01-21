if (args.length == 0) {
    println "Please enter project name where flyway migration sql file will be created."
    System.exit(101)
}

if (args.length == 1) {
    println "Please enter sql file name"
    System.exit(102)
}

if (!args[1].endsWith(".sql")) {
    println "Please enter file name with .sql suffix"
    System.exit(103)
}

final String apiName = args[0]
final String migrationSqlFileName = args[1]
final String migrationFileBasePath = "./" + apiName + "/infra/src/main/resources/db/migration/";
File migrationsFolder = new File(migrationFileBasePath)
if (!migrationsFolder.exists()) {
    migrationsFolder.mkdirs()
}

def timestamp = new Date().format('yyyyMMddHHmmssSSS', TimeZone.getTimeZone('Europe/Istanbul'))
String migrationFilePrefix = "V" + timestamp + "__"
String migrationFileNameWithPath = migrationFileBasePath + migrationFilePrefix + migrationSqlFileName

def flywayMigrationFile = new File(migrationFileNameWithPath)
flywayMigrationFile.createNewFile()
