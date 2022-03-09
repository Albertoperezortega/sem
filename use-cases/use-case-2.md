# USE CASE: 2 Produce various city reports

## CHARACTERISTIC INFORMATION

### Goal in Context

As a data analyst I want to produce various city reports so that I can help my organisation decide about future investments.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the order we want the countries to be generated in and the amount to be displayed. Database contains every country in the world.

### Success End Condition

A report is available for the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

Data analyst

### Trigger

A request for country reports is sent to the data analyst.

## MAIN SUCCESS SCENARIO

1. Organisation requests country reports.
2. Data analyst captures the correct reports to produce.
3. Data analyst produces and provides the correct reports for the organisation.

## EXTENSIONS

**Country does not exist**:
   1. Data analyst informs organization no such country exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0