# Google Compute Engine CLI 

## gcloud command

### List and select projects

When managing more then one project, you can list and select projects with:

```
$ gcloud projects list
PROJECT_ID           NAME                 PROJECT_NUMBER
compute-engine-6969  jsosic-foobar1       429052673518
compute-engine-6970  jsosic-foobar2       249042524163
```

```
gcloud config set project compute-engine-6969
```

### Serial console

Physical servers usually have some kind of remote console, like KVM, iLO,
iDRAC ... Google offers `Virtual Serial Console`, which is currently still
in beta. It is not as powerfull as iDRAC and relatives, but it does help.

To access it, you need to enable it first:

```
gcloud compute instances add-metadata [INSTANCE_NAME] --metadata=serial-port-enable=1
```

Now, create files:

```
~/.ssh/google_compute_engine
~/.ssh/google_compute_engine.pub
```
These files represent keys that are set in project metadata for an user present over there.


Afterwards you can connect to it by running:

```
gcloud beta compute connect-to-serial-port [USER]@[INSTANCE_NAME]
```

To disconnect from the serial console:

```
Press the ENTER key.
Type ~. (tilde, followed by a period).
```


### Log in docker to registry

Download keyfile and use it to log in:

```
docker login -u _json_key -p "$(cat keyfile.json)" https://gcr.io
```
