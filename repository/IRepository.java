package repository;

import exceptions.ADTException;
import model.programState.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addProgram(ProgramState program);

    void logPrgStateExec(ProgramState programState) throws ADTException, IOException;

    void emptyLogFile();

    List<ProgramState> getProgramList();

    void setProgramStates(List<ProgramState> programStates);
}
