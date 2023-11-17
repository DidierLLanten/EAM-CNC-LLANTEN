# Parcial 3

## Instructions 
- Create pv, pvc and pod objects inside a cluster
- Create a file inside the pod at the path ```/usr/share/nginx/html```
- Delete the pod and recreate it again, the information must persist (meaning the file you created).


## Solution

1. Create a directory for the project called parcial3
<br><br>

2. Inside parcial3, create three files.
- pv.yml
    ```yml
    piVersion: v1
    kind: PersistentVolume
    metadata:
      name: ejemplo-pv
    spec:
      capacity:
        storage: 5Gi
      volumeMode: Filesystem
      accessModes:
        - ReadWriteOnce
      persistentVolumeReclaimPolicy: Retain
      storageClassName: default
      hostPath:
        path: "/mnt/data"
      claimRef:
        namespace: default
        name: ejemplo-pvc  # Nombre del PersistentVolumeClaim vinculado
    ```

- pvc.yml
    ```yml
    apiVersion: v1
    kind: PersistentVolumeClaim
    metadata:
      name: ejemplo-pvc
    spec:
      accessModes:
        - ReadWriteOnce
      resources:
        requests:
          storage: 5Gi
      storageClassName: default
    ```

- pod-pvc.yml
    ```yml
    apiVersion: v1
    kind: Pod
    metadata:
      name: ejemplo-pod
    spec:
      containers:
      - name: mi-contenedor
        image: nginx:latest
        volumeMounts:
        - name: almacenamiento-volumen
          mountPath: "/usr/share/nginx/html"
      volumes:
      - name: almacenamiento-volumen
        persistentVolumeClaim:
          claimName: ejemplo-pvc
    ```
<br><br>

3. In the path ``.../parcial3`` execute the following commands

    **Create pv**
    ```sh
    sudo kubectl apply -f pv.yml
    ```

    **Review pv**
    ```sh
    sudo kubectl get pv
    ```

    **Create pvc**
    ```sh
    sudo kubectl apply -f pvc.yml
    ```
    **Review pv**
    ```sh
    sudo kubectl get pv
    ```
    **Create pod-pvc**
    ```sh
    sudo kubectl apply -f pod-pvc.yml
    ```

    **List pods**
    ```sh
    sudo kubectl get pods
    ```
<br><br>

4. Now go into the pod and create a file in the path ```/usr/share/nginx/html```.

    **Enter the pod**
    ```sh
    sudo kubectl exec -it ejemplo-pod -- bash
    ```


    **Move to html path**
    ```sh
    cd /usr/share/nginx/html
    ```

     ***To be able to create and edit files you need an editor, vim, vi, nano, etc...***.
    ```sh
    apt-get update
    apt-get install vim
    ```

    **Create a file, (add some info) ```install some editor inside the pod to be able to do this step, example: vim```**
    ```sh
    vim parcial3.html
    ```

    **List to view the created file**
    ```sh
    ls
    ```

<br><br>

5. Delete pod.

    **Exit pod**
    ```sh
    exit
    ```

    **Delete pod**
    ```sh
    sudo kubectl delete pod ejemplo-pod
    ```

    **List pods to check that it was eliminated**.
    ```sh
    sudo kubectl get pods
    ```
<br><br>

6. Persistence check.

    **Re-create the pod**
    ```sh
    sudo kubectl apply -f pod-pvc.yml
    ```

    **Enter the pod**
    ```sh
    sudo kubectl exec -it ejemplo-pod -- bash
    ```

    **Move to html path**
    ```sh
    cd /usr/share/nginx/html
    ```

    **Finally list to view the file (parcial3.html)**
    ```sh
    ls
    ```
