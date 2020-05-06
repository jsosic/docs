# Jenkins pipelines

## Generate pipeline

Easiest way is to use pipeline snippet generator:
- open any pipeline job and click on `Pipeline Syntax` in the sidebar
- pick any step in the drop down
- configure as you like and click `Generate Pipeline script`

[Example URI](https://jenkins.example.lan/job/my-nice-job-name/pipeline-syntax/).

## Kill a stuck job

```
def JobName = 'job-name'
def JobNumber = 44
Jenkins.instance.getItemByFullName(JobName)
                .getBuildByNumber(JobNumber)
                .finish(
                        hudson.model.Result.ABORTED,
                        new java.io.IOException("Aborting build")
                );
```
