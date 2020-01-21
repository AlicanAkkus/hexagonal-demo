Closure findFromAll = { findFromAll() }
Closure findFrom = { String projectName -> findMissingKeys(projectName) }

Map<String, Closure> commands = ["-all": findFromAll, "-api": findFrom]

if (commands.containsKey(args[0])) {
    commands.get(args[0]).call(args.length == 2 ? args[1] : null)
} else {
    println "Please enter a valid command!"
    System.exit(11)
}

private static void findFromAll() {
    File foldersInCurrentDirectory = new File(".")
    Set<String> searchedApiSet = [] as Set

    foldersInCurrentDirectory.traverse {
        if (it.name ==~ /(.*)-api/ && !searchedApiSet.contains(it.name)) {
            searchedApiSet.add(it.name)
            findMissingKeys(it.name)
        }
    }
}

private static findMissingKeys(String projectName) {
    String i18nPath = projectName + "/infra/src/main/resources/i18n/" + buildPackageName(projectName) + "_tr.properties"

    if (new File(i18nPath).exists()) {
        Set<String> exceptionKeysInProperties = new File(i18nPath)
                .collect { it.split("=")[0] }
                .findAll { !it.contains("#") }

        def projectDirectory = new File(projectName)
        Set<String> exceptionKeysInClasses = buildMissingKeys(projectDirectory)
        Set<String> exceptionKeysInClassesClone = buildMissingKeys(projectDirectory)

        exceptionKeysInClasses.removeAll(exceptionKeysInProperties)
        exceptionKeysInProperties.removeAll(exceptionKeysInClassesClone)

        printMissingKeys(exceptionKeysInClasses, projectName, "class")
        printMissingKeys(exceptionKeysInProperties, projectName, "i18n")
    }
}

private static Set<String> buildMissingKeys(File projectDirectory) {
    Set<String> suffixSet = ["groovy", "java"]

    Set<String> exceptionKeysInClasses = []

    projectDirectory.traverse {
        if (it.file && it.name.contains(".") && !it.name.contains("Test") && suffixSet.contains(it.name.split("\\.")[1])) {
            exceptionKeysInClasses.addAll(extractKeysFromClasses(it))
        }
    }

    return exceptionKeysInClasses
}

private static Set<String> extractKeysFromClasses(File it) {
    return it.readLines()
            .findAll { !it.contains("+") }
            .findAll { it ==~ /(.*Exception\(".*)|(.*validate(.*)\(.*".*)/ }
            .findAll { !it.contains("IllegalArgumentException") }
            .collect { it.split("\"")[1] }
            .findAll { it.contains(".") }

}

private static String buildPackageName(String projectName) {
    if (projectName.contains("-api"))
        projectName = projectName.replaceFirst("-api", "")

    String[] splittedName = projectName.split("-")
    StringBuilder packageNameBuilder = new StringBuilder()

    splittedName.each {
        packageNameBuilder.append(it)
    }

    return packageNameBuilder.toString()
}

private static void printMissingKeys(Set<String> missingKeySet, String projectName, String place) {
    println "\n----" + place + " " + projectName + "----------"

    if (missingKeySet.size() > 0)
        missingKeySet.each { println it }
    else
        println "All exception keys were defined in i18n files for " + projectName
}