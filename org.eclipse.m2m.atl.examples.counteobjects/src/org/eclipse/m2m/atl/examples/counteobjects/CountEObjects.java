package org.eclipse.m2m.atl.examples.counteobjects;

import java.util.function.Function;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.common.ATLLogger;
import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.ExecEnv;
import org.eclipse.m2m.atl.emftvm.Model;
import org.eclipse.m2m.atl.emftvm.util.DefaultModuleResolverFactory;
import org.eclipse.m2m.atl.emftvm.util.EMFTVMUtil;
import org.eclipse.m2m.atl.emftvm.util.ExecEnvPool;
import org.eclipse.m2m.atl.emftvm.util.TimingData;

public class CountEObjects implements Function<IFile, Model> {

	final ExecEnvPool execEnvPool = new ExecEnvPool();

	public CountEObjects() {
		execEnvPool.registerMetaModel(EcorePackage.eNAME.toUpperCase(), EMFTVMUtil.getEcoreMetamodel());
		execEnvPool.setModuleResolverFactory(
				new DefaultModuleResolverFactory("platform:/plugin/org.eclipse.m2m.atl.test-plugin/transformations/"));
		execEnvPool.loadModule("CountEObjects");
	}

	@Override
	public Model apply(final IFile inFile) {
		final TimingData timingData = new TimingData();

		final ResourceSet resourceSet = new ResourceSetImpl();

		final URI inFileURI = URI.createPlatformResourceURI(
				inFile.getProject().getName() + '/' + inFile.getProjectRelativePath().toString(), true);
		final Resource inResource = resourceSet.getResource(inFileURI, true);
		final Model inModel = EmftvmFactory.eINSTANCE.createModel(inResource);

		final Resource outResource = resourceSet.createResource(URI.createURI("out-temp.ecore"));
		final Model outModel = EmftvmFactory.eINSTANCE.createModel(outResource);

		final ExecEnv execEnv = execEnvPool.getExecEnv();
		execEnv.registerInputModel("IN", inModel);
		execEnv.registerOutputModel("OUT", outModel);

		timingData.finishLoading();

		execEnv.run(timingData);

		timingData.finish();
		ATLLogger.info(timingData.toString());

		return outModel;
	}

}
