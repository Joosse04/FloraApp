package org.izv.flora.model.entity;

public class DeleteResponse {

    public long rows;

    public DeleteResponse(){

    }

    @Override
    public String toString() {
        return "DeleteResponse{" +
                "rows=" + rows +
                '}';
    }
}
