package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.tasks.Deadline;
import ip.tasks.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddEventCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException, FileCorruptedException {

        if (input.length() == 5) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (!input.contains("/from") || !input.contains("/to")) {
            throw new UnknownInputException("Your Event has to have a duration inputted with '/from' and '/to'");
        }

        String[] splitInput = input.substring(6).split("/");

        if (splitInput[0].trim().isEmpty()) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (splitInput[1].startsWith("from ")) {
            splitInput[1] = splitInput[1].substring(5);
            if (splitInput[1].isEmpty()) {
                throw new UnknownInputException("Your Event has to have a start time inputted with '/from'");
            }
        } else {
            throw new UnknownInputException("Your Event has to have a start time inputted with '/from'");
        }

        if (splitInput[2].startsWith("to ")) {
            splitInput[2] = splitInput[2].substring(3);
        } else {
            throw new UnknownInputException("Your Event has to have a end time inputted with '/to'");
        }

        String startDate = splitInput[1].trim();
        String endDate = splitInput[2].trim();
        boolean startIsDate = DateValidator.isValid(startDate);
        boolean endIsDate = DateValidator.isValid(endDate);

        if (startIsDate) {
            LocalDate date = LocalDate.parse(startDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
            startDate = date.format(formatter);
        }

        if (endIsDate) {
            LocalDate date = LocalDate.parse(endDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
            endDate = date.format(formatter);
        }

        Event addTask = new Event(splitInput[0].trim(), startDate, endDate);

        try {
            FileWriter data = new FileWriter(Squiddy.DATA_PATHNAME, true);

            String dataString = addTask.toDataString() + "\n";

            data.write(dataString);
            data.close();
            Squiddy.tasks.add(addTask);

            System.out.print("Let me write this down: \n" +
                    addTask.toString().indent(8));
        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }
}
