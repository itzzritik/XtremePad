/*******************************************************************************
 * Copyright (c) 2012, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.mozilla.*;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

class FilePicker_17 extends FilePicker_1_8 {

void createCOMInterfaces () {
	/* Create each of the interfaces that this object implements */
	supports = new XPCOMObject (new int[] {2, 0, 0}) {
		public int /*long*/ method0 (int /*long*/[] args) {return QueryInterface (args[0], args[1]);}
		public int /*long*/ method1 (int /*long*/[] args) {return AddRef ();}
		public int /*long*/ method2 (int /*long*/[] args) {return Release ();}
	};

	filePicker = new XPCOMObject (new int[] {2, 0, 0, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}) {
		public int /*long*/ method0 (int /*long*/[] args) {return QueryInterface (args[0], args[1]);}
		public int /*long*/ method1 (int /*long*/[] args) {return AddRef ();}
		public int /*long*/ method2 (int /*long*/[] args) {return Release ();}
		public int /*long*/ method3 (int /*long*/[] args) {return Init (args[0], args[1], (short)args[2]);}
		public int /*long*/ method4 (int /*long*/[] args) {return AppendFilters ((int)/*64*/args[0]);}
		public int /*long*/ method5 (int /*long*/[] args) {return AppendFilter (args[0], args[1]);}
		public int /*long*/ method6 (int /*long*/[] args) {return GetDefaultString (args[0]);}
		public int /*long*/ method7 (int /*long*/[] args) {return SetDefaultString (args[0]);}
		public int /*long*/ method8 (int /*long*/[] args) {return GetDefaultExtension (args[0]);}
		public int /*long*/ method9 (int /*long*/[] args) {return SetDefaultExtension (args[0]);}
		public int /*long*/ method10 (int /*long*/[] args) {return GetFilterIndex (args[0]);}
		public int /*long*/ method11 (int /*long*/[] args) {return SetFilterIndex ((int)/*64*/args[0]);}
		public int /*long*/ method12 (int /*long*/[] args) {return GetDisplayDirectory (args[0]);}
		public int /*long*/ method13 (int /*long*/[] args) {return SetDisplayDirectory (args[0]);}
		public int /*long*/ method14 (int /*long*/[] args) {return GetFile (args[0]);}
		public int /*long*/ method15 (int /*long*/[] args) {return GetFileURL (args[0]);}
		public int /*long*/ method16 (int /*long*/[] args) {return GetFiles (args[0]);}
		public int /*long*/ method17 (int /*long*/[] args) {return XPCOM.NS_ERROR_NOT_IMPLEMENTED;}
		public int /*long*/ method18 (int /*long*/[] args) {return XPCOM.NS_ERROR_NOT_IMPLEMENTED;}
		public int /*long*/ method19 (int /*long*/[] args) {return Show (args[0]);}
		public int /*long*/ method20 (int /*long*/[] args) {return Open (args[0]);}
	};
}

int Open (int /*long*/ aFilePickerShownCallback) {
	if (mode == nsIFilePicker.modeGetFolder) {
		/* picking a directory */
		int result = showDirectoryPicker ();
		if (aFilePickerShownCallback != 0) {
			new nsIFilePickerShownCallback (aFilePickerShownCallback).Done (result);
		}
		return XPCOM.NS_OK;
	}

	/* picking a file */
	int style = mode == nsIFilePicker.modeSave ? SWT.SAVE : SWT.OPEN;
	if (mode == nsIFilePicker.modeOpenMultiple) style |= SWT.MULTI;
	Browser browser = getBrowser (parentHandle);
	Shell parent = null;
	if (browser != null) {
		parent = browser.getShell ();
	} else {
		parent = new Shell ();
	}
	FileDialog dialog = new FileDialog (parent, style);
	if (title != null) dialog.setText (title);
	if (directory != null) dialog.setFilterPath (directory);
	if (masks != null) dialog.setFilterExtensions (masks);
	if (defaultFilename != null) dialog.setFileName (defaultFilename);
	String filename = dialog.open ();
	files = dialog.getFileNames ();
	directory = dialog.getFilterPath ();
	title = defaultFilename = null;
	masks = null;
	int result = filename == null ? nsIFilePicker.returnCancel : nsIFilePicker.returnOK; 
	if (aFilePickerShownCallback != 0) {
		new nsIFilePickerShownCallback (aFilePickerShownCallback).Done (result);
	}
	return XPCOM.NS_OK;
}

}
