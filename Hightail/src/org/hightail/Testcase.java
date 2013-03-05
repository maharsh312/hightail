package org.hightail;

import java.io.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hightail.diff.OutputDiff;

public class Testcase implements Runnable {
    protected int index = 0;
    protected String input;
    protected String expectedOutput;
    protected String programOutput = "";
    protected String programError = "";
    protected ExecutionResult executionResult = new ExecutionResult();
    protected Process executionProcess;
    protected TestcaseSet callback;
    private String pathToExecFile;

    public Testcase() {
        this.input = "";
        this.expectedOutput = "";
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public String getProgramOutput() {
        return programOutput;
    }

    public void setProgramOutput(String programOutput) {
        this.programOutput = programOutput;
    }

    public ExecutionResult getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(ExecutionResult result) {
        this.executionResult = result;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }

    public Testcase(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }
    
    public void save() {
        // testcase has been changed by user, so results are obsolete
        emptyResultsOfTestCase();
    }

    public void emptyResultsOfTestCase() {
        programOutput = "";
        executionResult = new ExecutionResult();
    }
    
    public void setCallback(TestcaseSet cb) {
        callback = cb;
    }
    
    public void killTest() {
        try {
            executionProcess.exitValue();
        } catch (IllegalThreadStateException ex) {
            executionProcess.destroy();
            executionResult.setResult(ExecutionResult.WA); // TODO: ?
        }
    }

    @Override
    public void run() {
        try {
            String line;
            BufferedReader br;
            
            double startTime = Calendar.getInstance().getTimeInMillis();
            // TODO: measure CPU time of executionProcess instead
            
            // TODO: change path to running file
            executionProcess = Runtime.getRuntime().exec(pathToExecFile);
            
            OutputStream stdin = executionProcess.getOutputStream();
            InputStream stderr = executionProcess.getErrorStream();
            InputStream stdout = executionProcess.getInputStream();
            
            // writing input
            stdin.write(input.getBytes());
            stdin.flush();
            stdin.close();
            
            // reading stdout
            br = new BufferedReader(new InputStreamReader(stdout));
            programOutput = "";
            while ((line = br.readLine()) != null) {
                programOutput = programOutput + line + "\n";
            }
            br.close();

            // reading stderr
            br = new BufferedReader(new InputStreamReader(stderr));
            programError = "";
            while ((line = br.readLine()) != null) {
                programError = programError + line + "\n";
            }
            br.close();
            
            int execRes = executionProcess.waitFor();
            
            if (execRes == 0) {
                String res = OutputDiff.diff(expectedOutput, programOutput);
                if (res.equals("OK")) {
                    executionResult.setResult(ExecutionResult.OK);
                } else {
                    executionResult.setResult(ExecutionResult.WA);
                    executionResult.setMsg(res);
                }             
            } else if (execRes == 143) { // TODO: ?
                executionResult.setResult(ExecutionResult.ABORTED);
            } else {
                executionResult.setResult(ExecutionResult.RUNTIME);
            }            
            
            double endTime = Calendar.getInstance().getTimeInMillis();
            executionResult.setTime((endTime - startTime) / 1000.0);
            
            callback.notifyResultsOfSingleTestcase(index);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Testcase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // probably aborted (maybe by the OS?)
            executionResult.setResult(ExecutionResult.ABORTED);
        }
    }

    void setPathToExecFile(String pathToExecFile) {
        this.pathToExecFile = pathToExecFile;
    }
}
