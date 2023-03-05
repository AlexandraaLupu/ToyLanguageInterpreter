package repository;

import exceptions.ADTException;
import model.programState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private final String logFilePath;

    public Repository(ProgramState state, String logFilePath){
        programStates = new ArrayList<>();
        this.logFilePath = logFilePath;
        this.addProgram(state);
        this.emptyLogFile();
    }

    @Override
    public List<ProgramState> getProgramList() {
        return programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    @Override
    public void addProgram(ProgramState program) {

        programStates.add(program);
    }

    @Override
    public void logPrgStateExec(ProgramState programState) throws ADTException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(programState.programStateToString());
        logFile.close();
    }

    @Override
    public void emptyLogFile() {
        try{
            PrintWriter logFile;
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            logFile.close();
        }
        catch(IOException e) {

        }

    }
}
