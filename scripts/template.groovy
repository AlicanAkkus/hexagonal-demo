import groovy.transform.Memoized

def templateProjectMap = [j: "java-template", g: "groovy-template"]

if (args.length == 0) {
    println "Please enter the project name you want to create."
    System.exit(11)
}

if (args.length == 1 && args[0] == "test") {
    String sampleApiName = "sample-project-api"
    assert "SampleProjectApi" == buildFilePrefix(sampleApiName)
    assert "sampleProjectApi" == buildProjectVariablePrefix(sampleApiName)
    assert "sampleproject" == buildPackageName(sampleApiName)
    assert "sample_project" == buildPropertiesFileName(sampleApiName)
    assert "Sample Project Api" == buildDescription(sampleApiName)
    assert "SAMPLE_PROJECT_API" == buildReplicaName(sampleApiName)

    println "All tests have PASSED."
    System.exit(0)
}

if (args.length == 1 || !templateProjectMap.get(args[1])) {
    println "Please enter the project type you want to create. j | g"
    System.exit(13)
}

final String templateProjectName = templateProjectMap.get(args[1])
final String templateProjectPath = "api-templates/" + templateProjectName
final String createdProjectName = args[0]

def copyTemplateProject = "cp -R ${templateProjectPath} ${createdProjectName}".execute()

if (copyTemplateProject.waitFor() == 0)
    println "Template project copied."
else {
    println "Template project could not copied."
    System.exit(21)
}

Process process = [
        "/bin/bash",
        "-c",
        "find ./${createdProjectName} -depth -type d -name '${buildPackageName(templateProjectName)}' -execdir mv {} ${buildPackageName(createdProjectName)} \\;"
].execute()

if (process.waitFor() == 0)
    println "Package names updated successfully."
else {
    println "Package names could not updated."
    System.exit(22)
}

def createdProjectDirectory = new File(createdProjectName)

createdProjectDirectory.traverse {
    if (it.file) {
        String content = new File(it.path).text
        content = content.replaceAll(buildFilePrefix(templateProjectName), buildFilePrefix(createdProjectName))
        content = content.replaceAll(buildProjectVariablePrefix(templateProjectName), buildProjectVariablePrefix(createdProjectName))
        content = content.replaceAll(buildPackageName(templateProjectName), buildPackageName(createdProjectName))
        content = content.replaceAll(templateProjectName, createdProjectName)
        content = content.replaceAll(buildDescription(templateProjectName), buildDescription(createdProjectName))
        content = content.replaceAll(buildReplicaName(templateProjectName), buildReplicaName(createdProjectName))

        new File(it.path).withWriter {
            writer -> writer.write(content)
        }

        if (it.name.contains(buildFilePrefix(templateProjectName))) {
            String fileName = it.path
            it.renameTo new File(fileName.replaceFirst(buildFilePrefix(templateProjectName), buildFilePrefix(createdProjectName)))
        }

        if (it.name.contains(buildPropertiesFileName(templateProjectName))) {
            String fileName = it.path
            it.renameTo new File(fileName.replaceFirst(buildPropertiesFileName(templateProjectName), buildPropertiesFileName(createdProjectName)))
        }
    }
}

println "${createdProjectName} project created."
System.exit(0)

@Memoized
String buildFilePrefix(String projectName) {
    String[] splittedName = projectName.split("-")
    StringBuilder filePrefixBuilder = new StringBuilder()

    splittedName.each {
        filePrefixBuilder.append(it.capitalize())
    }

    return filePrefixBuilder.toString()
}

@Memoized
String buildProjectVariablePrefix(String projectName) {
    String filePrefix = buildFilePrefix(projectName)
    return Character.toLowerCase(filePrefix.charAt(0)).toString() + filePrefix.substring(1);
}

@Memoized
String buildPackageName(String projectName) {
    if (projectName.contains("-api"))
        projectName = projectName.replaceFirst("-api", "")

    String[] splittedName = projectName.split("-")
    StringBuilder packageNameBuilder = new StringBuilder()

    splittedName.each {
        packageNameBuilder.append(it)
    }

    return packageNameBuilder.toString()
}

@Memoized
String buildPropertiesFileName(String projectName) {
    if (projectName.contains("-api"))
        projectName = projectName.replaceFirst("-api", "")

    String[] splittedName = projectName.split("-")
    StringBuilder propertiesNameBuilder = new StringBuilder()

    splittedName.each {
        propertiesNameBuilder.append(it)
        propertiesNameBuilder.append("_")
    }

    return propertiesNameBuilder.deleteCharAt(propertiesNameBuilder.length() - 1).toString()
}

@Memoized
String buildDescription(String projectName) {
    String[] splittedName = projectName.split("-")
    StringBuilder descriptionBuilder = new StringBuilder()

    splittedName.each {
        descriptionBuilder.append(it.capitalize())
        descriptionBuilder.append(" ")
    }

    return descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1).toString()
}

@Memoized
String buildReplicaName(String projectName) {
    String[] splittedName = projectName.split("-")
    StringBuilder replicaNameBuilder = new StringBuilder()

    splittedName.each {
        replicaNameBuilder.append(it.toUpperCase())
        replicaNameBuilder.append("_")
    }

    return replicaNameBuilder.deleteCharAt(replicaNameBuilder.length() - 1).toString()
}
