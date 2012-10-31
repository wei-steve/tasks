/**
 * Copyright (c) 2012 Todoroo Inc
 *
 * See the file "LICENSE" for the full license governing this code.
 */
package com.todoroo.astrid.subtasks;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.timsu.astrid.R;
import com.todoroo.andlib.data.TodorooCursor;
import com.todoroo.astrid.activity.TaskListFragment;
import com.todoroo.astrid.adapter.TaskAdapter;
import com.todoroo.astrid.data.TagData;
import com.todoroo.astrid.data.Task;

/**
 * Fragment for subtasks
 *
 * @author Tim Su <tim@astrid.com>
 *
 */
public class SubtasksListFragment extends TaskListFragment {

    protected NewOrderedListFragmentHelper<?> helper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        helper = createFragmentHelper();
        super.onActivityCreated(savedInstanceState);
    }

    protected NewOrderedListFragmentHelper<?> createFragmentHelper() {
        NewOrderedListFragmentHelper<TagData> olfh =
            new NewOrderedListFragmentHelper<TagData>(this, new SubtasksUpdater());
        olfh.setList(getActiveTagData());
        return olfh;
    }

    @Override
    protected View getListBody(ViewGroup root) {
        return getActivity().getLayoutInflater().inflate(R.layout.task_list_body_subtasks, root, false);
    }

    @Override
    protected void setUpUiComponents() {
        super.setUpUiComponents();

        helper.setUpUiComponents();
    }

    @Override
    protected void setUpTaskList() {
        helper.beforeSetUpTaskList(filter);

        super.setUpTaskList();

        unregisterForContextMenu(getListView());
    }

    @Override
    protected boolean isDraggable() {
        return true;
    }

    @Override
    protected void onTaskDelete(Task task) {
        super.onTaskDelete(task);
        helper.onDeleteTask(task);
    }

    @Override
    protected TaskAdapter createTaskAdapter(TodorooCursor<Task> cursor) {
        return helper.createTaskAdapter(cursor, sqlQueryTemplate);
    }

}
