    /**
     * Returns the given InputStream if it is already a {@link BufferedInputStream}, otherwise creates a BufferedInputStream from the given
     * InputStream.
     *
     * @param inputStream
     *            the InputStream to wrap or return (not null)
     * @return the given InputStream or a new {@link BufferedInputStream} for the given InputStream
     * @since 2.5
     * @throws NullPointerException if the input parameter is null
     */
    public static BufferedInputStream buffer(final InputStream inputStream) {
        // reject null early on rather than waiting for IO operation to fail
        if (inputStream == null) { // not checked by BufferedInputStream
            throw new NullPointerException();
        }
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
    }



    /**
     * Returns the given reader if it is already a {@link BufferedReader}, otherwise creates a BufferedReader from the given
     * reader.
     *
     * @param reader
     *            the reader to wrap or return (not null)
     * @return the given reader or a new {@link BufferedReader} for the given reader
     * @since 2.5
     * @throws NullPointerException if the input parameter is null
     */
    public static BufferedReader buffer(final Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }



    /**
     * Returns the given Writer if it is already a {@link BufferedWriter}, otherwise creates a BufferedWriter from the given
     * Writer.
     *
     * @param writer
     *            the Writer to wrap or return (not null)
     * @return the given Writer or a new {@link BufferedWriter} for the given Writer
     * @since 2.5
     * @throws NullPointerException if the input parameter is null
     */
    public static BufferedWriter buffer(final Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }



    /**
     * Returns the given OutputStream if it is already a {@link BufferedOutputStream}, otherwise creates a BufferedOutputStream from the given
     * OutputStream.
     *
     * @param outputStream
     *            the OutputStream to wrap or return (not null)
     * @return the given OutputStream or a new {@link BufferedOutputStream} for the given OutputStream
     * @since 2.5
     * @throws NullPointerException if the input parameter is null
     */
    public static BufferedOutputStream buffer(final OutputStream outputStream) {
        // reject null early on rather than waiting for IO operation to fail
        if (outputStream == null) { // not checked by BufferedOutputStream
            throw new NullPointerException();
        }
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream);
    }



  public synchronized void stop() {
    open = false;
    notifyAll();
  }



  
  public List<UrlRewriteRuleDescriptor> getRules() {
    return ruleList;
  }



  
  public UrlRewriteRuleDescriptor getRule( String name ) {
    return ruleMap.get( name );
  }



  /**
   * verify that given paths exist in ZK
   * @param paths - paths to verify or create
   */
  public void validatePaths(String[] paths) {
    for (String path : paths) {
      if (!zkClient.exists(path)) {
        zkClient.createPersistent(path, true);
      }
    }
  }



  private int computeHashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((partition == null) ? 0 : partition.hashCode());
    return result;
  }



	BeanDefinitionRegistry resolveRegistry(ParserContext parserContext) {
		return parserContext.getRegistry();
	}



	protected Boolean getPdxIgnoreUnreadFields() {
		return this.pdxIgnoreUnreadFields;
	}



	protected Resource getCacheXml() {
		return this.cacheXml;
	}



	protected Float getCriticalHeapPercentage() {
		return this.criticalHeapPercentage;
	}



	protected TransactionWriter getTransactionWriter() {
		return this.transactionWriter;
	}



	protected DynamicRegionSupport getDynamicRegionSupport() {
		return this.dynamicRegionSupport;
	}



	protected PdxSerializer getPdxSerializer() {
		return this.pdxSerializer;
	}



	protected GatewayConflictResolver getGatewayConflictResolver() {
		return this.gatewayConflictResolver;
	}



	protected boolean getCopyOnRead() {
		return this.copyOnRead;
	}



	protected List<CacheFactoryBean.JndiDataSource> getJndiDataSources() {
		return nullSafeList(this.jndiDataSources);
	}



	protected String getPdxDiskStoreName() {
		return this.pdxDiskStoreName;
	}



	protected List<TransactionListener> getTransactionListeners() {
		return nullSafeList(this.transactionListeners);
	}



	protected Float getEvictionHeapPercentage() {
		return this.evictionHeapPercentage;
	}



	protected boolean isClose() {
		return this.close;
	}



	protected Boolean getPdxPersistent() {
		return this.pdxPersistent;
	}



	protected Boolean getPdxReadSerialized() {
		return this.pdxReadSerialized;
	}



	SpringContextBootstrappingInitializer newSpringContextBootstrappingInitializer() {
		return new SpringContextBootstrappingInitializer();
	}



	@Bean
	@SuppressWarnings("all")
	public BeanDefinitionRegistryPostProcessor cachingAnnotationsRegionBeanDefinitionRegistrar() {

		return new BeanDefinitionRegistryPostProcessorSupport() {

			
			public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
				registerBeanDefinitions(registry);
			}
		};
	}



	/**
	 * Returns a reference to the Spring {@link BeanFactory} in the current application context.
	 *
	 * @return a reference to the Spring {@link BeanFactory}.
	 * @throws IllegalStateException if the Spring {@link BeanFactory} was not properly configured.
	 * @see org.springframework.beans.factory.BeanFactory
	 */
	protected BeanFactory getBeanFactory() {
		return Optional.ofNullable(this.beanFactory)
			.orElseThrow(() -> newIllegalStateException("BeanFactory is required"));
	}



	/**
	 * Returns a reference to the Spring {@link Environment}.
	 *
	 * @return a reference to the Spring {@link Environment}.
	 * @see org.springframework.core.env.Environment
	 */
	protected Environment getEnvironment() {
		return this.environment;
	}



	/**
	 * Returns a reference to the {@link EvaluationContext} used to evaluate SpEL expressions.
	 *
	 * @return a reference to the {@link EvaluationContext} used to evaluate SpEL expressions.
	 * @see org.springframework.expression.EvaluationContext
	 */
	protected EvaluationContext getEvaluationContext() {
		return this.evaluationContext;
	}



	/**
	 * Returns a reference to the {@link ClassLoader} use by the Spring {@link BeanFactory} to load classes
	 * for bean definitions.
	 *
	 * @return the {@link ClassLoader} used by the Spring {@link BeanFactory} to load classes for bean definitions.
	 * @see #setBeanClassLoader(ClassLoader)
	 */
	protected ClassLoader getBeanClassLoader() {
		return this.beanClassLoader;
	}



	protected Boolean getMultiUserAuthentication() {
		return this.multiUserAuthentication;
	}



	protected Integer getReadTimeout() {
		return this.readTimeout;
	}



	protected boolean getReadyForEvents() {
		return this.readyForEvents;
	}



	protected Integer getStatisticsInterval() {
		return this.statisticsInterval;
	}



	protected Boolean getPrSingleHopEnabled() {
		return this.prSingleHopEnabled;
	}



	protected Integer getFreeConnectionTimeout() {
		return this.freeConnectionTimeout;
	}



	protected Iterable<ConnectionEndpoint> getPoolLocators() {
		return this.locators;
	}



	protected Integer getSubscriptionAckInterval() {
		return this.subscriptionAckInterval;
	}



	protected Integer getSubscriptionMessageTrackingTimeout() {
		return this.subscriptionMessageTrackingTimeout;
	}



	protected Boolean getSubscriptionEnabled() {
		return this.subscriptionEnabled;
	}



	protected Long getPingInterval() {
		return this.pingInterval;
	}



	protected Long getIdleTimeout() {
		return this.idleTimeout;
	}



	protected Boolean getKeepAlive() {
		return this.keepAlive;
	}



	protected Iterable<ConnectionEndpoint> getPoolServers() {
		return this.servers;
	}



	protected String getDurableClientId() {
		return this.durableClientId;
	}



	protected Integer getDurableClientTimeout() {
		return this.durableClientTimeout;
	}



	protected Integer getSubscriptionRedundancy() {
		return this.subscriptionRedundancy;
	}



	protected Integer getLoadConditioningInterval() {
		return this.loadConditioningInterval;
	}



	protected Integer getRetryAttempts() {
		return this.retryAttempts;
	}



	protected Integer getMinConnections() {
		return this.minConnections;
	}



	protected String getServerGroup() {
		return this.serverGroup;
	}



	protected Integer getSocketBufferSize() {
		return this.socketBufferSize;
	}



	protected Boolean getThreadLocalConnections() {
		return this.threadLocalConnections;
	}



	protected Integer getMaxConnections() {
		return this.maxConnections;
	}



	/**
	 * Creates a new {@link Region} with the given {@link String name}.
	 *
	 * This method gets called when a {@link Region} with the specified {@link String name} does not already exist.
	 * By default, this method implementation throws a {@link BeanInitializationException} and it is expected
	 * that {@link Class subclasses} will override this method.
	 *
	 * @param cache reference to the {@link GemFireCache}.
	 * @param regionName {@link String name} of the new {@link Region}.
	 * @return a new {@link Region} with the given {@link String name}.
	 * @throws BeanInitializationException by default unless a {@link Class subclass} overrides this method.
	 * @see org.apache.geode.cache.GemFireCache
	 * @see org.apache.geode.cache.Region
	 */
	protected Region<K, V> createRegion(GemFireCache cache, String regionName) throws Exception {
		throw new BeanInitializationException(
			String.format("Region [%1$s] in Cache [%2$s] not found", regionName, cache));
	}



	protected Integer getSocketBufferSize() {
		return Optional.ofNullable(this.socketBufferSize).orElse(CacheServer.DEFAULT_SOCKET_BUFFER_SIZE);
	}



	protected Long getLoadPollInterval() {
		return Optional.ofNullable(this.loadPollInterval).orElse(CacheServer.DEFAULT_LOAD_POLL_INTERVAL);
	}



	protected Integer getMessageTimeToLive() {
		return Optional.ofNullable(this.messageTimeToLive).orElse(CacheServer.DEFAULT_MESSAGE_TIME_TO_LIVE);
	}



	protected SubscriptionEvictionPolicy getSubscriptionEvictionPolicy() {
		return Optional.ofNullable(this.subscriptionEvictionPolicy).orElse(SubscriptionEvictionPolicy.DEFAULT);
	}



	protected Set<InterestRegistrationListener> getInterestRegistrationListeners() {
		return nullSafeSet(this.interestRegistrationListeners);
	}



	protected Integer getMaxConnections() {
		return Optional.ofNullable(this.maxConnections).orElse(CacheServer.DEFAULT_MAX_CONNECTIONS);
	}



	protected Integer getMaxTimeBetweenPings() {
		return Optional.ofNullable(this.maxTimeBetweenPings).orElse(CacheServer.DEFAULT_MAXIMUM_TIME_BETWEEN_PINGS);
	}



	protected String getBindAddress() {
		return Optional.ofNullable(this.bindAddress).filter(StringUtils::hasText)
			.orElse(CacheServer.DEFAULT_BIND_ADDRESS);
	}



	protected ServerLoadProbe getServerLoadProbe() {
		return Optional.ofNullable(this.serverLoadProbe).orElse(CacheServer.DEFAULT_LOAD_PROBE);
	}



	protected Integer getMaxThreads() {
		return Optional.ofNullable(this.maxThreads).orElse(CacheServer.DEFAULT_MAX_THREADS);
	}



	protected String getHostnameForClients() {
		return Optional.ofNullable(this.hostnameForClients).filter(StringUtils::hasText)
			.orElse(CacheServer.DEFAULT_HOSTNAME_FOR_CLIENTS);
	}



	protected boolean isAutoStartup() {
		return this.autoStartup;
	}



	protected Integer getPort() {
		return Optional.ofNullable(this.port).orElse(CacheServer.DEFAULT_PORT);
	}



	protected Integer getSubscriptionCapacity() {
		return Optional.ofNullable(this.subscriptionCapacity).orElse(ClientSubscriptionConfig.DEFAULT_CAPACITY);
	}



	protected Integer getMaxMessageCount() {
		return Optional.ofNullable(this.maxMessageCount).orElse(CacheServer.DEFAULT_MAXIMUM_MESSAGE_COUNT);
	}



	protected String getSubscriptionDiskStoreName() {
		return this.subscriptionDiskStoreName;
	}



	/**
	 * Returns a reference to an instance of the {@link AbstractCacheConfiguration} class used to configure
	 * a GemFire (Singleton, client or peer) cache instance along with it's associated, embedded services.
	 *
	 * @param <T> {@link Class} type extension of {@link AbstractCacheConfiguration}.
	 * @return a reference to a single {@link AbstractCacheConfiguration} instance.
	 * @throws IllegalStateException if the {@link AbstractCacheConfiguration} reference was not configured.
	 * @see org.springframework.data.gemfire.config.annotation.AbstractCacheConfiguration
	 */
	@SuppressWarnings("unchecked")
	protected <T extends AbstractCacheConfiguration> T getCacheConfiguration() {
		return Optional.ofNullable((T) this.cacheConfiguration)
			.orElseThrow(() -> newIllegalStateException("AbstractCacheConfiguration is required"));
	}



  public String urlFor(
    RequestContext request, 
    Object key, 
    Object param) {
      Template template = templates.get(key);
      return template != null ? template.expand(getContext(request,param)) : null;
  }



    /**
     * Creates a <i>Message-ID</i> field for the specified host name.
     *
     * @param hostname
     *            host name to be included in the message ID or
     *            <code>null</code> if no host name should be included.
     * @return the newly created <i>Message-ID</i> field.
     */
    public static UnstructuredField generateMessageId(String hostname) {
        String fieldValue = MimeUtil.createUniqueMessageId(hostname);
        return parse(UnstructuredFieldImpl.PARSER, FieldName.MESSAGE_ID, fieldValue);
    }



    public SingleBodyBuilder use(final BodyFactory bodyFactory) {
        this.bodyFactory = bodyFactory;
        return this;
    }



	@SuppressWarnings("unchecked")
	public String postVideo(Resource video) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("file", video);
		Map<String, Object> response = restTemplate.postForObject("https://graph-video.facebook.com/me/videos", parts, Map.class);
		return (String) response.get("id");
	}



	@SuppressWarnings("unchecked")
	public String postVideo(Resource video, String title, String description) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("file", video);
		parts.set("title", title);
		parts.set("description", description);
		Map<String, Object> response = restTemplate.postForObject("https://graph-video.facebook.com/me/videos", parts, Map.class);
		return (String) response.get("id");
	}



	public String postPhoto(Resource photo) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		return graphApi.publish("me", "photos", parts);
	}



	public String postPhoto(String albumId, Resource photo) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		return graphApi.publish(albumId, "photos", parts);
	}



	public String postPhoto(Resource photo, String caption) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		parts.set("message", caption);
		return graphApi.publish("me", "photos", parts);
	}



	public String postPhoto(String albumId, Resource photo, String caption) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.set("source", photo);
		parts.set("message", caption);
		return graphApi.publish(albumId, "photos", parts);
	}



    private void discardCacheStartingWith(int index) {
        while (index < cachedPages.size()) {
            this.cachedPages.remove(cachedPages.size() - 1);
            if (!pageSeq.goToPreviousSimplePageMaster()) {
                log.warn("goToPreviousSimplePageMaster() on the first page called!");
            }
        }
    }



    /**
     * Add the characters to this BookmarkTitle.
     * The text data inside the BookmarkTitle xml element
     * is used for the BookmarkTitle string.
     *
     * @param data the character data
     * @param start the start position in the data array
     * @param length the length of the character array
     * @param pList currently applicable PropertyList
     * @param locator location in fo source file.
     */
    protected void characters(char[] data, int start, int length,
                                 PropertyList pList,
                                 Locator locator) {
        title += new String(data, start, length);
    }



    public boolean hasClip() {
        return this.clip;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 2;
    }



    private void serializeXMLizable(XMLizable object) throws IFException {
        try {
            object.toSAX(handler);
        } catch (SAXException e) {
            throw new IFException("SAX error serializing object", e);
        }
    }



    public boolean hasClip() {
        return clip;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 1;
    }



    /**
     * Save the current painting state.
     * This pushes the current painting state onto the stack.
     * This call should be used when the Q operator is used
     * so that the state is known when popped.
     */
    public void save() {
        AbstractData copy = (AbstractData)getData().clone();
        stateStack.push(copy);
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 1;
    }



    /**
     * Creates and returns a PageBreakingLayoutListener for the PageBreakingAlgorithm to
     * notify about layout problems.
     * @return the listener instance or null if no notifications are needed
     */
    protected PageBreakingAlgorithm.PageBreakingLayoutListener createLayoutListener() {
        return null;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 3;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 0;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 2;
    }



    /**
     * Return the "rule-thickness" property.
     */
    public Length getRuleThickness() {
        return ruleThickness;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 1;
    }



    /**
     * Adds instance of <code>OpenGroupMark</code> as a child with attributes.
     * 
     * @param attrs  attributes to add
     * @throws IOException for I/O problems
     */
    public void addOpenGroupMark(RtfAttributes attrs) throws IOException {
        RtfOpenGroupMark r = new RtfOpenGroupMark(this, writer, attrs);
    }



    /**
     * Updates the message to be shown in the info bar in a thread safe way.
     */
    public void notifyPageRendered() {
        SwingUtilities.invokeLater(new ShowInfo());
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 1;
    }



    /** {@inheritDoc} */
    public int getRequiredArgsCount() {
        return 1;
    }



    /**
     * Clears and resets the cache.
     * @throws IOException if there is an error closing the stream
     */
    public void clear() throws IOException {
        if (output != null) {
            output.close();
            output = null;
        }
    }



    
    /** {@inheritDoc} */
    public int getOptionalArgsCount() {
        return 1;
    }



    public boolean hasActiveVariant() {
        return whitespaceManagementPosition.getKnuthList() != null;
    }



    
    /** {@inheritDoc} */
    public int getOptionalArgsCount() {
        return 1;
    }



    
    /** {@inheritDoc} */
    public int getOptionalArgsCount() {
        return 1;
    }



    /**
     * Return the "precedence" property.
     */
    public int getPrecedence() {
        return precedence;
    }



  /**
   * Check to see if two-way SSL auth should be used between server and agents
   * or not
   *
   * @return true two-way SSL authentication is enabled
   */
  public boolean isTwoWaySsl() {
    return Boolean.parseBoolean(getProperty(SRVR_TWO_WAY_SSL));
  }



  @Bean
  public LogsearchCorsFilter logsearchCorsFilter() {
    return new LogsearchCorsFilter(logSearchHttpHeaderConfig);
  }



  @Bean(name = "eventHistorySolrTemplate")
  @DependsOn("serviceSolrTemplate")
  public SolrTemplate eventHistorySolrTemplate() {
    return null;
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



    @GET
    @Produces("text/json")
    @Path("/metrics")
    public Response getOkResponse() throws IOException {
        return Response.ok().build();
    }



  /**
   * Adds a mapping for a new aggregate definition.
   *
   * @param clusterId
   *          the ID of the cluster that the definition is bound to.
   * @param definition
   *          the aggregate definition to register (not {@code null}).
   */
  public void registerAggregate(long clusterId, AlertDefinition definition) {
    Long id = Long.valueOf(clusterId);

    if (!m_aggregateMap.containsKey(id)) {
      m_aggregateMap.put(id, new HashMap<String, AlertDefinition>());
    }

    Map<String, AlertDefinition> map = m_aggregateMap.get(id);

    AggregateSource as = (AggregateSource) definition.getSource();

    map.put(as.getAlertName(), definition);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  public static String append(String node, String child) {
    return node.endsWith("/") ? node + child : node + "/" + child;
  }



  @Bean
  @DependsOn({"serviceSolrTemplate", "auditSolrTemplate"})
  public SolrSchemaFieldDao solrSchemaFieldDao() {
    return new SolrSchemaFieldDao();
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errors.add(error);
  }



  
  public void addError(String error) {
    errors.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  @JsonProperty("is_visible")
  public boolean isVisible() {
    return visibility;
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  /**
   * Filter hosts according to status of host/host components
   * @param hosts Host name set to filter
   * @param actionExecutionContext Received request to execute a command
   * @param resourceFilter Resource filter
   * @return Set of excluded hosts
   * @throws AmbariException
   */
  private Set<String> getUnhealthyHosts(Set<String> hosts,
                                          ActionExecutionContext actionExecutionContext,
                                          RequestResourceFilter resourceFilter) throws AmbariException {
    Set<String> removedHosts = new HashSet<String>();
    for (String hostname : hosts) {
      if (filterUnhealthHostItem(hostname, actionExecutionContext, resourceFilter)){
        removedHosts.add(hostname);
      }
    }
    hosts.removeAll(removedHosts);
    return removedHosts;
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  
  public void addError(String error) {
    errorSet.add(error);
  }



  /**
   * Given a principal, attempt to create a new DeconstructedPrincipal
   *
   * @param principal a String containing the principal to deconstruct
   * @return a DeconstructedPrincipal
   * @throws KerberosOperationException
   */
  protected DeconstructedPrincipal createDeconstructPrincipal(String principal) throws KerberosOperationException {
    try {
      return DeconstructedPrincipal.valueOf(principal, getDefaultRealm());
    } catch (IllegalArgumentException e) {
      throw new KerberosOperationException(e.getMessage(), e);
    }
  }



  
  public String toString() {
    return "expr(" + expression.sourceText + ")";
  }



  void doRotate(int lo, int mid, int hi) {
    if (mid - lo == hi - mid) {
      // happens rarely but saves n/2 swaps
      while (mid < hi) {
        swap(lo++, mid++);
      }
    } else {
      reverse(lo, mid);
      reverse(mid, hi);
      reverse(lo, hi);
    }
  }



  /**
   * Returns the ValueSource description.
   */
  
  public String toString() {
    return "DistanceValueSource("+strategy+", "+from+")";
  }



  
  public Lock obtainLock(Directory dir, String lockName) {
    return SINGLETON_LOCK;
  }



  
  public void parse(Reader in) throws IOException, ParseException {
    LineNumberReader br = new LineNumberReader(in);
    try {
      addInternal(br);
    } catch (IllegalArgumentException e) {
      ParseException ex = new ParseException("Invalid synonym rule at line " + br.getLineNumber(), 0);
      ex.initCause(e);
      throw ex;
    } finally {
      br.close();
    }
  }



  
  public int size() {
    return -1;
  }



  /** Returns byte usage of all buffers. */
  
  public long ramBytesUsed() {
    return (long) file.numBuffers() * (long) BUFFER_SIZE;
  }



  
  public Lock obtainLock(String name) {
    throw new UnsupportedOperationException();
  }



  /**
   * Close the {@link ClientConnectionManager} from the internal client.
   */
  
  public void close() throws IOException {
    if (httpClient != null && internalClient) {
      HttpClientUtil.close(httpClient);
    }
  }



  public boolean getAndResetApplyAllDeletes() {
    return flushDeletes.getAndSet(false);
  }



  
  public String toString() {
    return getClass().getSimpleName()+"("+provider+", "+from+")";
  }



  
  public int size() {
    return -1;
  }



  /**
   * Forcibly refresh cluster state from ZK. Do this only to avoid race conditions because it's expensive.
   *
   * It is cheaper to call {@link #forceUpdateCollection(String)} on a single collection if you must.
   * 
   * @lucene.internal
   */
  public void forciblyRefreshAllClusterStateSlow() throws KeeperException, InterruptedException {
    synchronized (getUpdateLock()) {
      if (clusterState == null) {
        // Never initialized, just run normal initialization.
        createClusterStateWatchersAndUpdate();
        return;
      }
      // No need to set watchers because we should already have watchers registered for everything.
      refreshCollectionList(null);
      refreshLiveNodes(null);
      refreshLegacyClusterState(null);
      // Need a copy so we don't delete from what we're iterating over.
      Collection<String> safeCopy = new ArrayList<>(watchedCollectionStates.keySet());
      Set<String> updatedCollections = new HashSet<>();
      for (String coll : safeCopy) {
        DocCollection newState = fetchCollectionState(coll, null);
        if (updateWatchedCollection(coll, newState)) {
          updatedCollections.add(coll);
        }
      }
      constructState(updatedCollections);
    }
  }



  
  public long ramBytesUsed() {
    return bytesUsed.get();
  }



  /**
   * Return total size in bytes of all files in this directory. This is
   * currently quantized to RAMOutputStream.BUFFER_SIZE.
   */
  
  public final long ramBytesUsed() {
    ensureOpen();
    return sizeInBytes.get();
  }



  /**
   * Shutdown all cores within the EmbeddedSolrServer instance
   */
  
  public void close() throws IOException {
    coreContainer.shutdown();
  }



  /**
   * Optional method: Return a {@link TwoPhaseIterator} view of this
   * {@link Scorer}. A return value of {@code null} indicates that
   * two-phase iteration is not supported.
   *
   * Note that the returned {@link TwoPhaseIterator}'s
   * {@link TwoPhaseIterator#approximation() approximation} must
   * advance synchronously with the {@link #iterator()}: advancing the
   * approximation must advance the iterator and vice-versa.
   *
   * Implementing this method is typically useful on {@link Scorer}s
   * that have a high per-document overhead in order to confirm matches.
   *
   * The default implementation returns {@code null}.
   */
  public TwoPhaseIterator twoPhaseIterator() {
    return null;
  }



  
  public long ramBytesUsed() {
    return globalBufferedUpdates.bytesUsed.get();
  }



  
  public String toString() {
    return "bboxShape(" + strategy.getFieldName() + ")";
  }



  /** For debugging -- used by CheckIndex too*/
  public Stats getStats() throws IOException {
    return new SegmentTermsEnum(this).computeBlockStats();
  }



  public void put(Object key, Object value) {
    this.fields.put(key, value);
  }



  /** Returns byte size of the underlying TST */
  
  public long ramBytesUsed() {
    long mem = RamUsageEstimator.shallowSizeOf(this);
    if (root != null) {
      mem += root.sizeInBytes();
    }
    return mem;
  }



  
  public long size() {
    return pointCount;
  }



  
  public void close() {
    if (aliveCheckExecutor != null) {
      aliveCheckExecutor.shutdownNow();
    }
    if(clientIsInternal) {
      HttpClientUtil.close(httpClient);
    }
  }



  
  public long size() throws IOException {
    return -1;
  }



  
  public synchronized long ramBytesUsed() {
    return sizeInBytes;
  }



  
  public int pointNumBytes() {
    return dimensionNumBytes;
  }



  
  public int pointDimensionCount() {
    return dimensionCount;
  }



  /**
   * Implemented as:
   * <code>
   * 1/sqrt( steepness * (abs(x-min) + abs(x-max) - (max-min)) + 1 )
   * </code>.
   *
   * <p>
   * This degrades to <code>1/sqrt(x)</code> when min and max are both 1 and
   * steepness is 0.5
   * </p>
   *
   * <p>
   * :TODO: potential optimization is to just flat out return 1.0f if numTerms
   * is between min and max.
   * </p>
   *
   * @see #setLengthNormFactors
   * @see <a href="doc-files/ss.computeLengthNorm.svg">An SVG visualization of this function</a> 
   */
  
  public float lengthNorm(int numTerms) {
    final int l = ln_min;
    final int h = ln_max;
    final float s = ln_steep;
  
    return (float)
      (1.0f /
       Math.sqrt
       (
        (
         s *
         (float)(Math.abs(numTerms - l) + Math.abs(numTerms - h) - (h-l))
         )
        + 1.0f
        )
       );
  }



  
  public final int longBlockCount() {
    return BLOCK_COUNT;
  }



  
  public int longValueCount() {
    return valueCount;
  }



  /** Assert that the current doc is -1. */
  protected final void checkUnpositioned(DocIdSetIterator iter) {
    if (iter.docID() != -1) {
      throw new IllegalStateException("This operation only works with an unpositioned iterator, got current position = " + iter.docID());
    }
  }



  
  public String toString() {
    return "bboxShape(" + strategy.getFieldName() + ")";
  }



  /**
   * Returns the ValueSource description.
   */
  
  public String toString() {
    return "DistanceValueSource("+strategy+", "+from+")";
  }



  
  protected void doInit() {
    if (reports.isEmpty()) { // set defaults
      reports = DEFAULT_REPORTS;
    }
  }



  public boolean getHighlightRequireFieldMatch() {
    return this.getBool(HighlightParams.FIELD_MATCH, false);
  }



	public boolean isWaitForRecording() {
		return waitForRecording;
	}



	public boolean isAllowUserQuestions() {
		return allowUserQuestions;
	}



	
	public String toId(User u) {
		String id = "" + u.getId();
		if (u.getId() == null) {
			newContacts.put(u.getLogin(), u);
			id = u.getLogin();
		}
		return id;
	}



	public boolean isAllowRecording() {
		return allowRecording;
	}



	public boolean isClosed() {
		return isClosed;
	}



	public boolean isPending() {
		return pending;
	}



	public boolean isUsed() {
		return used;
	}



	private void processRestore(File backup) throws Exception {
		try (InputStream is = new FileInputStream(backup)) {
			BackupImport importCtrl = getApplicationContext().getBean(BackupImport.class);
			importCtrl.performImport(is);
		}
	}



	public boolean isShowContactDataToContacts() {
		return showContactDataToContacts;
	}



	public boolean isShowContactData() {
		return showContactData;
	}



    /**
     * Getter for the tokenizer model for the parsed language.
     * If the model is not yet available a new one is built. The required data
     * are loaded by using the {@link DataFileProvider} service.  
     * @param language the language
     * @return the model or <code>null</code> if no model data are found
     * @throws InvalidFormatException in case the found model data are in the wrong format
     * @throws IOException on any error while reading the model data
     */
    public TokenizerModel getTokenizerModel(String language) throws InvalidFormatException, IOException {
        return initModel(String.format("%s-token.bin", language),TokenizerModel.class);
    }



    /**
     * Getter for the chunker model for the parsed language.
     * If the model is not yet available a new one is built. The required data
     * are loaded by using the {@link DataFileProvider} service.  
     * @param language the language
     * @return the model or <code>null</code> if no model data are present
     * @throws InvalidFormatException in case the found model data are in the wrong format
     * @throws IOException on any error while reading the model data
     */
    public ChunkerModel getChunkerModel(String language) throws InvalidFormatException, IOException {
        return initModel(String.format("%s-chunker.bin", language), ChunkerModel.class);
    }



    /**
     * Getter for the named entity finder model for the parsed entity type and language.
     * If the model is not yet available a new one is built. The required data
     * are loaded by using the {@link DataFileProvider} service.  
     * @param type the type of the named entities to find (person, organization)
     * @param language the language
     * @return the model or <code>null</code> if no model data are found
     * @throws InvalidFormatException in case the found model data are in the wrong format
     * @throws IOException on any error while reading the model data
     */
    public TokenNameFinderModel getNameModel(String type, String language) throws InvalidFormatException, IOException {
        return initModel(String.format("%s-ner-%s.bin", language, type),
            TokenNameFinderModel.class);
    }



    /**
     * Getter for the sentence detection model of the parsed language. 
     * If the model is not yet available a new one is built. The required data
     * are loaded by using the {@link DataFileProvider} service.  
     * @param language the language
     * @return the model or <code>null</code> if no model data are found
     * @throws InvalidFormatException in case the found model data are in the wrong format
     * @throws IOException on any error while reading the model data
     */
    public SentenceModel getSentenceModel(String language) throws InvalidFormatException, IOException {
        return initModel(String.format("%s-sent.bin", language),
            SentenceModel.class);
    }



    
    public String prettyPrint() {
        return string;
    }



    /**
     * Adds an Token that negates this Sentiment
     * @param token the token
     */
    protected void addNegate(Token token){
        if(negated == null){ //most of the time a singeltonList will do
            negated = Collections.singletonList(token);
        } else if(negated.size() == 1){
            List<Token> l = new ArrayList<Token>(4);
            l.add(negated.get(0));
            l.add(token);
            negated = l;
        }
        checkSpan(token);
    }



    /**
     * Sets up the connection pool, by creating a pooling driver.
     * @return Driver
     * @throws SQLException
     */
    private synchronized ConnectionPool pCreatePool() throws SQLException {
        if (pool != null) {
            return pool;
        } else {
            pool = new ConnectionPool(poolProperties);
            return pool;
        }
    }



    
    protected void doClose() {
        channel.close();
    }



    /**
     * TODO SERVLET 3.1
     */
    
    public boolean isReady() {
        // TODO Auto-generated method stub
        return false;
    }



    /**
     * Disable swallowing of remaining input if configured
     */
    protected void checkSwallowInput() {
        Context context = getContext();
        if (context != null && !context.getSwallowAbortedUploads()) {
            coyoteRequest.action(ActionCode.DISABLE_SWALLOW_INPUT, null);
        }
    }



    public void heartbeat() {
        if ( clusterSender!=null ) clusterSender.heartbeat();
        super.heartbeat();
    }



    public ServletRegistration getServletRegistration(String servletName) {
        return null;
    }



    public FilterRegistration getFilterRegistration(String filterName) {
        return null;
    }



    
    protected InputStream doGetInputStream() {
        return null;
    }



    /**
     * Calc processing stats
     */
    public boolean doStatistics() {
        return doProcessingStats;
    }



    public Jar openJar() throws IOException {
        if (entryName == null) {
            return null;
        } else {
            return JarFactory.newInstance(url);
        }
    }



    public static void set() {
        marker.set(Boolean.TRUE);
    }



    public AbstractRxTask createRxTask() {
        NioReplicationTask thread = new NioReplicationTask(this,this);
        thread.setUseBufferPool(this.getUseBufferPool());
        thread.setRxBufSize(getRxBufSize());
        thread.setOptions(getWorkerThreadOptions());
        return thread;
    }



    public void setEventSubType(EventSubType eventSubType) {
        this.eventSubType = eventSubType;
    }



    protected void checkExpiration() {
        long timeout = maxIdleTimeout;
        if (timeout < 1) {
            return;
        }

        if (System.currentTimeMillis() - lastActive > timeout) {
            String msg = sm.getString("wsSession.timeout");
            doClose(new CloseReason(CloseCodes.GOING_AWAY, msg),
                    new CloseReason(CloseCodes.CLOSED_ABNORMALLY, msg));
        }
    }



    public boolean isUseEquals() {
        return useEquals;
    }



    public AbstractRxTask createRxTask() {
        return getReplicationThread();
    }



    public static long secsToMillis (int secs) {
        return 1000*(long) secs;
    }



    private HBMessage pathExists(String path, boolean authenticated) {
        HBMessage response = null;
        if (authenticated) {
            boolean itDoes = heartbeats.containsKey(path);
            LOG.debug("Checking if path [ {} ] exists... {} .", path, itDoes);
            response = new HBMessage(HBServerMessageType.EXISTS_RESPONSE, HBMessageData.boolval(itDoes));
        } else {
            response = notAuthorized();
        }
        return response;
    }



    public boolean isSubSystemMounted(SubSystemType subsystem) {
        for (SubSystemType type : this.subSystems) {
            if (type == subsystem) {
                return true;
            }
        }
        return false;
    }



    
    protected void process(Tuple input) {
        try {
            final Map<String, List<Object>> scoresPerStream = runner.scoredTuplePerStream(input);
            LOG.debug("Input tuple [{}] generated predicted scores [{}]", input, scoresPerStream);
            if (scoresPerStream != null) {
                for (Map.Entry<String, List<Object>> streamToTuple : scoresPerStream.entrySet()) {
                    collector.emit(streamToTuple.getKey(), input, streamToTuple.getValue());
                }
                collector.ack(input);
            } else {
                LOG.debug("Input tuple [{}] generated NULL scores", input);
            }
        } catch (Exception e) {
            collector.reportError(e);
            collector.fail(input);
        }
    }



    public static boolean isOnWindows() {
        if (System.getenv("OS") != null) {
            return System.getenv("OS").equals("Windows_NT");
        }
        return false;
    }



    public MoveFileAction toDestination(String destDir){
        destination = destDir;
        return this;
    }



    
    public void process(Tuple tuple) {
        try {
            Collection<Values> values = lookupValuesInEs(tuple);
            tryEmitAndAck(values, tuple);
        } catch (Exception e) {
            collector.reportError(e);
            collector.fail(tuple);
        }
    }



    
    public void close() {
        if(client != null) {
            client.close();
            client = null;
        }
    }



    
    protected void process(Tuple tuple) {
        try {
            List<Column> columns = jdbcMapper.getColumns(tuple);
            List<List<Column>> columnLists = new ArrayList<List<Column>>();
            columnLists.add(columns);
            if(!StringUtils.isBlank(tableName)) {
                this.jdbcClient.insert(this.tableName, columnLists);
            } else {
                this.jdbcClient.executeInsertQuery(this.insertQuery, columnLists);
            }
            this.collector.ack(tuple);
        } catch (Exception e) {
            this.collector.reportError(e);
            this.collector.fail(tuple);
        }
    }



    
    public void credentialsChanged(Credentials credentials) {
        TupleImpl tuple = new TupleImpl(executor.getWorkerTopologyContext(), new Values(credentials), (int) Constants.SYSTEM_TASK_ID,
                Constants.CREDENTIALS_CHANGED_STREAM_ID);
        List<AddressedTuple> addressedTuple = Lists.newArrayList(new AddressedTuple(AddressedTuple.BROADCAST_DEST, tuple));
        executor.getReceiveQueue().publish(addressedTuple);
    }



    /**
     * Get a set of SubSystemType objects from a comma delimited list of subsystem names
     */
    public static Set<SubSystemType> getSubSystemsFromString(String str) {
        Set<SubSystemType> result = new HashSet<SubSystemType>();
        String[] subSystems = str.split(",");
        for (String subSystem : subSystems) {
            //return null to mount options in string that is not part of cgroups
            SubSystemType type = SubSystemType.getSubSystem(subSystem);
            if (type != null) {
                result.add(type);
            }
        }
        return result;
    }



    /**
     * Get a string that is a comma delimited list of subsystems
     */
    public static String subSystemsToString(Set<SubSystemType> subSystems) {
        StringBuilder sb = new StringBuilder();
        if (subSystems.size() == 0) {
            return sb.toString();
        }
        for (SubSystemType type : subSystems) {
            sb.append(type.name()).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }



    void __setState(int i)
    {
        m_state = i;
    }



    
    public Deserializing<T> convertWith(Converter c) {
        converter = c;
        return this;
    }



    
    public Deserializing<T> parseWith(Parser p) {
        parser = p;
        return this;
    }



	/**
	 * Called by constructors and when deserialized.
	 * 
	 * @param mask
	 */
	private synchronized void setTransients(int mask) {
		if ((mask == ACTION_NONE) || ((mask & ACTION_ALL) != mask)) {
			throw new IllegalArgumentException("invalid action string");
		}

		action_mask = mask;
	}



    /**
     * Get the current bundle protection domains on the stack up to the last 
     * privileged call.
     */
    public static List grab()
    {
        // First try to get a cached version. We cache by thread.
        DomainGripper gripper = (DomainGripper) m_cache.get();
        if (gripper == null)
        {
            // there is none so create one and cache it
            gripper = new DomainGripper();
            m_cache.set(gripper);
        }
        else
        {
            // This thread has a cached version so prepare it
            gripper.m_domains.clear();
        }

        // Get the current context.
        gripper.m_system = AccessController.getContext();

        // and merge it with the current combiner (i.e., gripper)
        AccessControlContext context =
            (AccessControlContext) AccessController.doPrivileged(gripper);

        gripper.m_system = null;

        // now get the protection domains
        AccessController.doPrivileged(gripper, context);

        // and return them
        return gripper.m_domains;
    }



	
    public FutureDependencyBuilder<F> complete(Object callbackInstance, String callback) {
	    super.setCallbacks(callbackInstance, callback, null);
	    return this;
	}



    public ConfigurationDependencyBuilder update(String update) {
        checkHasNoMethodRefs();
        m_hasReflectionCallback = true;
        m_updateMethodName = update;
        return this;
    }



    void closeSession(CommandSessionImpl session)
    {
        synchronized (sessions)
        {
            sessions.remove(session);
        }
    }



    /**
     * @param repository maven repository
     * @param artifact maven artifact
     * @return file URI pointing to artifact in repository
     */
    public static URI getArtifactURI( ArtifactRepository repository, Artifact artifact )
    {
        String baseDir = repository.getBasedir();
        String artifactPath = repository.pathOf( artifact );

        return toFileURI( baseDir + '/' + artifactPath );
    }



    
    public Deserializing<T> parseWith(Parser p) {
        parser = p;
        return this;
    }



    
    public Deserializing<T> convertWith(Converter c) {
        converter = c;
        return this;
    }



    private ConfigurationException thresholdFailure(final Object invalidValue)
    {
        return new ConfigurationException(MemoryUsageConstants.PROP_DUMP_THRESHOLD, "Invalid Dump Threshold value '"
            + invalidValue + "': Must be an integer number in the range " + MemoryUsageConstants.MIN_DUMP_THRESHOLD
            + " to " + MemoryUsageConstants.MAX_DUMP_THRESHOLD + " or zero to disable");
    }



    public void addCapability() throws BundleException
    {
        throw new BundleException("Not implemented yet.");
    }



    
    public FactoryPidAdapterBuilder update(String update) {
        checkHasNoMethodRefs();
        m_hasReflectionCallback = true;
        m_updateMethodName = update;
        return this;
    }



    /**
     * Returns the attached factory.
     * Before returning the factory, the consistency of the
     * factory is checked.
     * @return the attached factory to the current component type
     */
    private Factory ensureAndGetFactory() {
        ensureFactory();
        return getFactory();
    }



    
    public String validValuePattern()
    {
        return _validValuePattern;
    }



    
    public String validValuePattern()
    {
        return _annotation.validValuePattern();
    }



    private Set<Preference> doGetPreferences()
    {
        Principal currentPrincipal = getMainPrincipalOrThrow();

        Set<Preference> preferences = new TreeSet<>(PREFERENCE_COMPARATOR);
        for (Preference preference : _preferences.values())
        {
            if (principalsEqual(currentPrincipal, preference.getOwner()))
            {
                preferences.add(preference);
            }
        }
        return preferences;
    }



    private Set<Preference> doGetVisiblePreferences()
    {
        Set<Principal> currentPrincipals = getPrincipalsOrThrow();

        Set<Preference> visiblePreferences = new TreeSet<>(PREFERENCE_COMPARATOR);
        for (Preference preference : _preferences.values())
        {
            if (principalsContain(currentPrincipals, preference.getOwner()))
            {
                visiblePreferences.add(preference);
                continue;
            }
            final Set<Principal> visibilityList = preference.getVisibilityList();
            if (visibilityList != null)
            {
                for (Principal principal : visibilityList)
                {
                    if (principalsContain(currentPrincipals, principal))
                    {
                        visiblePreferences.add(preference);
                        break;
                    }
                }
            }
        }

        return visiblePreferences;
    }



    public boolean wantsWrite()
    {
        return !_fullyWritten;
    }



    public boolean wantsRead()
    {
        return _fullyWritten;
    }



    
    protected MessageStore createMessageStore()
    {
        return _configurationStore.getMessageStore();
    }



    private void checkForNotification(final ServerMessage<?> msg,
                                      final QueueNotificationListener listener,
                                      final long currentTime,
                                      final long thresholdTime,
                                      final NotificationCheck check)
    {
        if (check.isMessageSpecific() || (_lastNotificationTimes[check.ordinal()] < thresholdTime))
        {
            if (check.notifyIfNecessary(msg, this, listener))
            {
                _lastNotificationTimes[check.ordinal()] = currentTime;
            }
        }
    }



    void addRule(Integer number,
                 String identity,
                 RuleOutcome ruleOutcome,
                 LegacyOperation operation,
                 ObjectType object,
                 AclRulePredicates predicates)
    {
        AclAction aclAction = new AclAction(operation, object, predicates);
        addRule(number, identity, ruleOutcome, aclAction);
    }



    void addRule(Integer number, String identity, RuleOutcome ruleOutcome, LegacyOperation operation)
    {
        AclAction action = new AclAction(operation);
        addRule(number, identity, ruleOutcome, action);
    }



    void addRule(Integer number,
                 String identity,
                 RuleOutcome ruleOutcome,
                 LegacyOperation operation,
                 ObjectType object,
                 ObjectProperties properties)
    {
        AclAction action = new AclAction(operation, object, properties);
        addRule(number, identity, ruleOutcome, action);
    }



    private void sendFlow(boolean flow)
    {
        MethodRegistry methodRegistry = _connection.getMethodRegistry();
        AMQMethodBody responseBody = methodRegistry.createChannelFlowBody(flow);
        _connection.writeFrame(responseBody.generateFrame(_channelId));
    }



    private int convertToIntWithDefault(String key, Map<String, String> context, int defaultValue)
    {
        if (context.containsKey(key))
        {
            try
            {
                return Integer.parseInt(context.get(key));
            }
            catch (NumberFormatException e)
            {
               return defaultValue;
            }
        }
        else
        {
            return defaultValue;
        }
    }



    
    public boolean makeAcquisitionStealable()
    {
        return false;
    }



    private void ensureSameTypeAndOwnerForExistingPreferences(final Collection<Preference> preferences)
    {
        Principal currentPrincipal = getMainPrincipalOrThrow();

        for (Preference preference : preferences)
        {
            if (preference.getId() != null && _preferences.containsKey(preference.getId()))
            {
                Preference existingPreference = _preferences.get(preference.getId());
                if (!preference.getType().equals(existingPreference.getType())
                    || !principalsEqual(existingPreference.getOwner(), currentPrincipal))
                {
                    throw new IllegalArgumentException(String.format("Preference Id '%s' already exists",
                                                                     preference.getId()));
                }
            }
        }
    }



    
    protected int performSize() {
        return triples.size();
    }



    public DataByteArrayOutputStream internalOutputStream() {
        return outputStream;
    }



    public void preProcessDispatch(MessageDispatch messageDispatch) {
        throw new BrokerStoppedException(this.message);
    }



    public static List x_queryMBeans(MBeanServerConnection jmxConnection, List queryList) throws Exception {
        // If there is no query defined get all mbeans
        if (queryList == null || queryList.size() == 0) {
            return createMBeansObjectNameQuery(jmxConnection).query("");

            // Parse through all the query strings
        } else {
            return createMBeansObjectNameQuery(jmxConnection).query(queryList);
        }
    }



    /**
     * Set system properties
     */
    protected void addActiveMQSystemProperties() {
        // Set the default properties
        System.setProperty("activemq.base", project.getBuild().getDirectory() + "/");
        System.setProperty("activemq.home", project.getBuild().getDirectory() + "/");
        System.setProperty("org.apache.activemq.UseDedicatedTaskRunner", "true");
        System.setProperty("org.apache.activemq.default.directory.prefix", project.getBuild().getDirectory() + "/");
        System.setProperty("derby.system.home", project.getBuild().getDirectory() + "/");
        System.setProperty("derby.storage.fileSyncTransactionLog", "true");

        // Overwrite any custom properties
        System.getProperties().putAll(systemProperties);
    }



    /**
     * @return the assigned QoS value for this subscription.
     */
    public QoS getQoS() {
        return qos;
    }



    void unregisterSender(ConsumerId consumerId) {
        subscriptionsByConsumerId.remove(consumerId);
    }



    void registerSender(ConsumerId consumerId, AmqpSender sender) {
        subscriptionsByConsumerId.put(consumerId, sender);
    }



    private void forceDestinationWakeupOnCompletion(ConnectionContext context, Transaction transaction,
                                                    ActiveMQDestination amqDestination, BaseCommand ack) throws Exception {
        Destination destination =  addDestination(context, amqDestination, false);
        registerSync(destination, transaction, ack);
    }



    /**
     * {@inheritDoc}
     */
    public long calculateMemoryFootprint() {
        /*
        private PropertyId id;
        private InternalValue[] values;
        private int type;
        private boolean multiValued;
        private PropDefId defId;

        we assume an average QName localname of 30 chars.
        PropertyId = 8 + nodeId(36) * name(250) + hash(4) ~ 300;
        NodeDefId = 8 + id(4) = 12
        InternalValue = 8 + n * (values) ~ 8 + n*100;
        value=approx 100 bytes.
        */
        return 350 + values.length * 100;
    }



    public final boolean isIdentifierBased() {
        return identifier;
    }



    private void endBatch(boolean successful) {
        if (--lockLevel == 0) {
            try {
                conHelper.endBatch(successful);;
            } catch (SQLException e) {
                log.error("failed to end batch", e);
            }
        }
    }



    /**
     * Increments the reference count of this reader. Each call to this method
     * must later be acknowledged by a call to {@link #release()}.
     */
    synchronized void acquire() {
        refCount++;
    }



    public void dispose() {
        if (temp) {
            delete(true);
        }
    }



    /**
     * Returns <code>false</code> as this is the root path.
     *
     * @return <code>false</code>
     */
    public boolean isIdentifierBased() {
        return false;
    }



    
    public void write(IFrameWriter outWriter, boolean clearFrame) throws HyracksDataException {
        getBuffer().clear();
        if (getTupleCount() > 0) {
            outWriter.nextFrame(getBuffer());
        }
        if (clearFrame) {
            frame.reset();
            reset(getBuffer(), true);
        }
    }



    /**
     * {@inheritDoc}
     */
    public void dropForeignKey(Table table, ForeignKey foreignKey) throws IOException
    {
        writeTableAlterStmt(table);
        print("DROP FOREIGN KEY ");
        printIdentifier(getForeignKeyName(table, foreignKey));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void createPrimaryKey(Table table, Column[] primaryKeyColumns) throws IOException
    {
        // Note that SapDB does not support the addition of named primary keys
        if ((primaryKeyColumns.length > 0) && shouldGeneratePrimaryKeys(primaryKeyColumns))
        {
            print("ALTER TABLE ");
            printlnIdentifier(getTableName(table));
            printIndent();
            print("ADD ");
            writePrimaryKeyStmt(table, primaryKeyColumns);
            printEndOfStatement();
        }
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        // Index names in DB2 are unique to a schema and hence Derby does not
        // use the ON <tablename> clause
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * Generates the statement to drop a non-embedded index from the database.
     *
     * @param table The table the index is on
     * @param index The index to drop
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        if (getPlatformInfo().isAlterTableForDropUsed())
        {
            writeTableAlterStmt(table);
        }
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        if (!getPlatformInfo().isAlterTableForDropUsed())
        {
            print(" ON ");
            printIdentifier(getTableName(table));
        }
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        // Index names in Oracle are unique to a schema and hence Oracle does not
        // use the ON <tablename> clause
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropForeignKey(Table table, ForeignKey foreignKey) throws IOException
    {
        writeTableAlterStmt(table);
        print("DROP FOREIGN KEY ");
        printIdentifier(getForeignKeyName(table, foreignKey));
        printEndOfStatement();

        // InnoDB won't drop the auto-index for the foreign key automatically, so we have to do it
        if (foreignKey.isAutoIndexPresent())
        {
            writeTableAlterStmt(table);
            print("DROP INDEX ");
            printIdentifier(getForeignKeyName(table, foreignKey));
            printEndOfStatement();
        }
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        // Index names in Firebird are unique to a schema and hence Firebird does not
        // use the ON <tablename> clause
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        // Index names in Derby are unique to a schema and hence Derby does not
        // use the ON <tablename> clause
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        // Index names in Interbase are unique to a schema and hence we do not
        // need the ON <tablename> clause
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        print("DROP INDEX ");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropForeignKey(Table table, ForeignKey foreignKey) throws IOException
    {
        writeTableAlterStmt(table);
        print("DROP CONSTRAINT ");
        printIdentifier(getForeignKeyName(table, foreignKey));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void createPrimaryKey(Table table, Column[] primaryKeyColumns) throws IOException
    {
        if ((primaryKeyColumns.length > 0) && shouldGeneratePrimaryKeys(primaryKeyColumns))
        {
            print("ALTER TABLE ");
            printlnIdentifier(getTableName(table));
            printIndent();
            print("ADD CONSTRAINT ");
            printIdentifier(getConstraintName(null, table, "PK", null));
            print(" ");
            writePrimaryKeyStmt(table, primaryKeyColumns);
            printEndOfStatement();
        }
    }



    /**
     * {@inheritDoc}
     */
    public void dropForeignKey(Table table, ForeignKey foreignKey) throws IOException
    {
        String constraintName = getForeignKeyName(table, foreignKey);

        print("IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'F' AND name = ");
        printAlwaysSingleQuotedIdentifier(constraintName);
        println(")");
        printIndent();
        print("ALTER TABLE ");
        printIdentifier(getTableName(table));
        print(" DROP CONSTRAINT ");
        printIdentifier(constraintName);
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        print("DROP INDEX ");
        printIdentifier(getTableName(table));
        print(".");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * If quotation mode is on, then this writes the statement that turns on the ability to write delimited identifiers.
     */
    protected void turnOnQuotation() throws IOException
    {
        print(getQuotationOnStatement());
    }



    /**
     * Determines whether the index is an internal index, i.e. one created by Derby.
     * 
     * @param index The index to check
     * @return <code>true</code> if the index seems to be an internal one
     */
    private boolean isInternalIndex(Index index)
    {
        String name = index.getName();

        // Internal names normally have the form "SQL051228005030780"
        if ((name != null) && name.startsWith("SQL"))
        {
            try
            {
                Long.parseLong(name.substring(3));
                return true;
            }
            catch (NumberFormatException ex)
            {
                // we ignore it
            }
        }
        return false;
    }



    /**
     * {@inheritDoc}
     */
    public void dropIndex(Table table, Index index) throws IOException
    {
        print("DROP INDEX ");
        printIdentifier(getTableName(table));
        print(".");
        printIdentifier(getIndexName(index));
        printEndOfStatement();
    }



    /**
     * {@inheritDoc}
     */
    public void dropForeignKey(Table table, ForeignKey foreignKey) throws IOException
    {
        String constraintName = getForeignKeyName(table, foreignKey);

        print("IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'RI' AND name = ");
        printAlwaysSingleQuotedIdentifier(constraintName);
        println(")");
        printIndent();
        print("ALTER TABLE ");
        printIdentifier(getTableName(table));
        print(" DROP CONSTRAINT ");
        printIdentifier(constraintName);
        printEndOfStatement();
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    private static boolean testForMicrosoftWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        //see org.apache.commons.lang.SystemUtils.IS_WINDOWS
        return os.startsWith("windows");
    }



    public void registerClass(Class<?> clazz) {
        loadedClasses.put(clazz.getName(), clazz);
    }



    /**
     * @deprecated since 0.6.0; kept only for persisted state backwards compatibility.
     */
    @Deprecated
    @SuppressWarnings({ "serial", "unused" })
    private static NotificationFilter unused_matchesTypeRegex(final String typeRegex) {
        return new NotificationFilter() {
             public boolean isNotificationEnabled(Notification notif) {
                return notif.getType().matches(typeRegex);
            }
        };
    }



    
    public Integer get() {
        synchronized (mutex) {
            return sensors().get(SEQUENCE_VALUE);
        }
    }



    
    protected void installBundlesAndRebuildCatalog() {
        checkEnteringPhase(2);
        
        // skip; bundles and catalog items can't be changed for a partial rebind instruction
        // (if upgrading, they should be changed beforehand, then this used to upgrade the objects)
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    
    public void setManagementContext(ManagementContext managementContext) {
        this.managementContext = managementContext;
    }



    /** Returns a list of {@link SpecParameterIncludingDefinitionForInheritance} objects from the given list;
     * these should be resolved against ancestors before using, converting that object to {@link BasicSpecParameter} instances. */
    public static List<SpecParameter<?>> parseParameterDefinitionList(List<?> obj, Function<Object, Object> specialFlagsTransformer, BrooklynClassLoadingContext loader) {
        return ParseYamlInputs.parseParameters(obj, specialFlagsTransformer, loader);
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



  /**
   * L2-norm 
   */
  public static double l2norm(double[] v) {
    return Math.sqrt(innerProduct(v, v));
  }



	/**
	 * The factory method for the {@link Sftp#outboundAdapter}.
	 * @param sessionFactory the {@link SessionFactory} to use.
	 * @return an {@link SftpMessageHandlerSpec} instance.
	 * @since 1.1.1
	 */
	public SftpMessageHandlerSpec sftp(SessionFactory<ChannelSftp.LsEntry> sessionFactory) {
		return Sftp.outboundAdapter(sessionFactory);
	}



	/**
	 * @param headerMapper the headerMapper.
	 * @return the spec.
	 * @see ChannelPublishingJmsMessageListener#setHeaderMapper(JmsHeaderMapper)
	 * @since 1.2
	 */
	public S headerMapper(JmsHeaderMapper headerMapper) {
		this.target.getListener().setHeaderMapper(headerMapper);
		return _this();
	}



	
	public void configure(IntegrationFlowDefinition<?> flow) {
		throw new UnsupportedOperationException();
	}



	public S jmsMessageConverter(MessageConverter messageConverter) {
		this.jmsChannelFactoryBean.setMessageConverter(messageConverter);
		return _this();
	}



	
	public JdbcServices initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		return new OgmJdbcServicesImpl();
	}



	
	public ConnectionProvider initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		return new NoopConnectionProvider();
	}



	
	public DialectFactory initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		return new OgmDialectFactory( registry );
	}



	
	public PersisterClassResolver initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		return new OgmPersisterClassResolver();
	}



	
	public SessionFactoryServiceRegistryFactory initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		return new OgmSessionFactoryServiceRegistryFactoryImpl( registry );
	}



    /**
     * Escape the characters in a <code>String</code> to be suitable to use as an HTTP parameter value.
     * <br/>
     * Uses UTF-8 as default character encoding.
     * @param string the string to escape, may be null
     * @return a new escaped <code>String</code>, <code>null</code> if null string input
     *
     * See java.net.URLEncoder#encode(String,String).
     */
    public String url(Object string) {
        if (string == null) {
            return null;
        }
        try {
            return URLEncoder.encode(String.valueOf(string),"UTF-8");
        } catch(UnsupportedEncodingException uee) {
            return null;
        }
    }



    /**
     * Returns a new ClassTool instance that is inspecting the
     * superclass of the Class being inspected by this instance.
     * If the current inspectee has no super class,
     * then this will return {@code null}. All other
     * configuration settings will be copied to the new instance.
     */
    public ClassTool getSuper()
    {
        Class sup = getType().getSuperclass();
        if (sup == null)
        {
            return null;
        }
        return inspect(sup);
    }



	public int size() {
		return this.fields.size();
	}



  public void enforceSortAlgorithm(int pid, SortEnforce.SortAlgorithm algorithm) {
    EnforceProperty.Builder builder = newProperty();
    SortEnforce.Builder enforce = SortEnforce.newBuilder();
    enforce.setPid(pid);
    enforce.setAlgorithm(algorithm);

    builder.setType(EnforceType.SORT);
    builder.setSort(enforce.build());
    TUtil.putToNestedList(properties, builder.getType(), builder.build());
  }



  public void enforceJoinAlgorithm(int pid, JoinEnforce.JoinAlgorithm algorithm) {
    EnforceProperty.Builder builder = newProperty();
    JoinEnforce.Builder enforce = JoinEnforce.newBuilder();
    enforce.setPid(pid);
    enforce.setAlgorithm(algorithm);

    builder.setType(EnforceType.JOIN);
    builder.setJoin(enforce.build());
    TUtil.putToNestedList(properties, builder.getType(), builder.build());
  }



  /**
   * It attaches a generated column name with a sequence id. It always keeps generated names unique.
   */
  private String attachSeqIdToGeneratedColumnName(String prefix) {
    int sequence = noNameColumnId++;
    return NONAMED_COLUMN_PREFIX + prefix.toLowerCase() + (sequence > 0 ? "_" + sequence : "");
  }



  public static String normalizeInetSocketAddress(InetSocketAddress addr) {
    return addr.getAddress().getHostAddress() + ":" + addr.getPort();
  }



  public int bitsLength() {
    return length;
  }



  
  public boolean hasReversedEdge(V head, V tail) {
    return reversedEdges.containsKey(head) && reversedEdges.get(head).containsKey(tail);
  }



  
  public void removeEdge(V tail, V head) {
    if (directedEdges.containsKey(tail)) {
      directedEdges.get(tail).remove(head);
      if (directedEdges.get(tail).isEmpty()) {
        directedEdges.remove(tail);
      }

      reversedEdges.get(head).remove(tail);
      if (reversedEdges.get(head).isEmpty()) {
        reversedEdges.remove(head);
      }
    } else {
      throw new RuntimeException("Not connected channel: " + tail + " -> " + head);
    }
  }



  
  public int getVertexSize() {
    return directedEdges.size();
  }



  
  public void addEdge(V tail, V head, E edge) {
    TUtil.putToNestedMap(directedEdges, tail, head, edge);
    TUtil.putToNestedMap(reversedEdges, head, tail, edge);
  }



  
  public boolean hasEdge(V tail, V head) {
    return directedEdges.containsKey(tail) && directedEdges.get(tail).containsKey(head);
  }



  public void init(String tableName, boolean purge) {
    this.tableName = tableName;
    this.purge = purge;
  }



  private boolean assertRelationExistence(VerificationState state, String name) {
    if (!catalog.existsTable(name)) {
      state.addVerification(String.format("relation \"%s\" does not exist", name));
      return false;
    }
    return true;
  }



  
  public Datum[] getValues() {
    Datum[] datums = new Datum[values.length];
    for (int i = 0; i < values.length; i++) {
      datums[i] = get(i);
    }
    return datums;
  }



    public void init() throws Exception {
        channel = endpoint.getServiceUnit().getComponent().getComponentContext().getDeliveryChannel();
    }



    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getProcessor().process(request, response);
        } catch (IOException e) {
            throw e;
        } catch (ServletException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException("Failed to process request: " + e, e);
        }
    }



    public void init() throws Exception {
        String url = endpoint.getLocationURI();
        context = new EndpointComponentContext(endpoint);
        channel = context.getDeliveryChannel();
        httpContext = getServerManager().createContext(url, this);
    }



  public TPermissionsUpdate toThrift() {
    return tPermUpdate;
  }



  /**
   * Grant a sentry role to groups.
   *
   * @param requestorUserName: user on whose behalf the request is issued
   * @param roleName:          Name of the role
   * @param component:         The request is issued to which component
   * @param groups:            The name of groups
   * @throws SentryUserException
   */
  
  public void grantRoleToGroups(String requestorUserName, String roleName,
                              String component, Set<String> groups) throws SentryUserException {
    TAlterSentryRoleAddGroupsRequest request = new TAlterSentryRoleAddGroupsRequest();
    request.setProtocol_version(sentry_common_serviceConstants.TSENTRY_SERVICE_V2);
    request.setRequestorUserName(requestorUserName);
    request.setRoleName(roleName);
    request.setGroups(groups);
    request.setComponent(component);

    try {
      TAlterSentryRoleAddGroupsResponse response = client.alter_sentry_role_add_groups(request);
      Status.throwIfNotOk(response.getStatus());
    } catch (TException e) {
      throw new SentryUserException(THRIFT_EXCEPTION_MESSAGE, e);
    }
  }



  /**
   * revoke a sentry role from groups.
   *
   * @param requestorUserName: user on whose behalf the request is issued
   * @param roleName:          Name of the role
   * @param component:         The request is issued to which component
   * @param groups:            The name of groups
   * @throws SentryUserException
   */
  
  public void revokeRoleFromGroups(String requestorUserName, String roleName,
                                 String component, Set<String> groups) throws SentryUserException {
    TAlterSentryRoleDeleteGroupsRequest request = new TAlterSentryRoleDeleteGroupsRequest();
    request.setProtocol_version(sentry_common_serviceConstants.TSENTRY_SERVICE_V2);
    request.setRequestorUserName(requestorUserName);
    request.setRoleName(roleName);
    request.setGroups(groups);
    request.setComponent(component);

    try {
      TAlterSentryRoleDeleteGroupsResponse response = client.alter_sentry_role_delete_groups(request);
      Status.throwIfNotOk(response.getStatus());
    } catch (TException e) {
      throw new SentryUserException(THRIFT_EXCEPTION_MESSAGE, e);
    }
  }



  public TPathsUpdate toThrift() {
    return tPathsUpdate;
  }



	
	public String getSaveResultsToBundle() {
		if (! hasSaveResultsToBundle()) { 
			return null;
		}
		return getOptionValue(BUNDLE);
	}



	
	protected TypeInformation<?> doGetComponentType() {

		Type componentType = type.getGenericComponentType();
		return createInfo(componentType);
	}



	boolean isParameterRequired() {
		return getNumberOfArguments() > 0;
	}



	
	public boolean isWritable() {
		return !isTransient();
	}



	/**
	 * Creates a fresh, cacheable {@link ProjectionInformation} instance for the given projection type.
	 * 
	 * @param projectionType must not be {@literal null}.
	 * @return
	 */
	protected ProjectionInformation createProjectionInformation(Class<?> projectionType) {
		return new DefaultProjectionInformation(projectionType);
	}



    public EncryptionType getEncryptionType()
    {
        return EncryptionType.DES_CBC_MD5;
    }



    public EncryptionType getEncryptionType()
    {
        return EncryptionType.NULL;
    }



    public EncryptionType getEncryptionType()
    {
        return EncryptionType.DES_CBC_CRC;
    }



    private byte[] processChecksum( byte[] data, byte[] key )
    {
        try
        {
            SecretKey sk = new SecretKeySpec( key, "AES" );

            Mac mac = Mac.getInstance( "HmacSHA1" );
            mac.init( sk );

            return mac.doFinal( data );
        }
        catch ( GeneralSecurityException nsae )
        {
            nsae.printStackTrace();
            return null;
        }
    }



    /**
     * returns the number of nodes present in this tree.
     * 
     * @return the number of nodes present in this tree
     */
    public int size()
    {
        return size;
    }



    /**
     * @return the replSearchSizeLimit
     */
    public int getReplSearchSizeLimit()
    {
        return replSearchSizeLimit;
    }



  /**
   * Returns an immutable static DecoderFactory configured with default settings
   * All mutating methods throw IllegalArgumentExceptions. All creator methods
   * create objects with default settings.
   */
  public static DecoderFactory get() {
    return DEFAULT_FACTORY;
  }



    /**
     * Is media file shared for gallery
     * 
     */
    public Boolean getSharedForGallery() {
        return isSharedForGallery;
    }



    private String[] createCategoryRestrictionAsArray() {
        if (catArray == null && getCategoryRestriction() != null) {
            StringTokenizer toker = new StringTokenizer(getCategoryRestriction(),",");
            catArray = new String[toker.countTokens()];
            int i = 0;
            
            while (toker.hasMoreTokens()) {
                catArray[i++] = toker.nextToken();
            }
        }
        return catArray;
    }



	
	protected QueryException generateQueryException(String queryString) {
		return new QueryParameterException( super.getOriginalMessage(), queryString, this );
	}



	
	protected QueryException generateQueryException(String queryString) {
		return new QuerySyntaxException( getOriginalMessage(), queryString, this );
	}



	
	public String getColumnDefinitionUniquenessFragment(org.hibernate.mapping.Column column) {
		return "";
	}



	protected SessionFactoryBuilder delegate() {
		return delegate;
	}



	private void bindValue(T value) {
		this.isBound = true;
		this.bindValue = value;

		if ( bindType == null ) {
			this.bindType = typeResolver.resolveParameterBindType( value );
		}
	}



	public boolean supportsLimitOffset() {
		return false;
	}



	/**
	 * Applies one or more strategy selectors announced as available by the passed announcer.
	 *
	 * @param strategyRegistrationProvider An provider for one or more available selectors
	 *
	 * @return {@code this}, for method chaining
	 *
	 * @see org.hibernate.boot.registry.selector.spi.StrategySelector#registerStrategyImplementor(Class, String, Class)
	 */
	@SuppressWarnings( {"UnusedDeclaration"})
	public BootstrapServiceRegistryBuilder applyStrategySelectors(StrategyRegistrationProvider strategyRegistrationProvider) {
		for ( StrategyRegistration strategyRegistration : strategyRegistrationProvider.getStrategyRegistrations() ) {
			this.strategySelectorBuilder.addExplicitStrategyRegistration( strategyRegistration );
		}
		return this;
	}



	/**
	 * Applies a named strategy implementation to the bootstrap registry.
	 *
	 * @param strategy The strategy
	 * @param name The registered name
	 * @param implementation The strategy implementation Class
	 * @param <T> Defines the strategy type and makes sure that the strategy and implementation are of
	 * compatible types.
	 *
	 * @return {@code this}, for method chaining
	 *
	 * @see org.hibernate.boot.registry.selector.spi.StrategySelector#registerStrategyImplementor(Class, String, Class)
	 */
	@SuppressWarnings( {"UnusedDeclaration"})
	public <T> BootstrapServiceRegistryBuilder applyStrategySelector(Class<T> strategy, String name, Class<? extends T> implementation) {
		this.strategySelectorBuilder.addExplicitStrategyRegistration( strategy, implementation, name );
		return this;
	}



	/**
	 * Adds a provided {@link ClassLoader} for use in class-loading and resource-lookup.
	 *
	 * @param classLoader The class loader to use
	 *
	 * @return {@code this}, for method chaining
	 */
	public BootstrapServiceRegistryBuilder applyClassLoader(ClassLoader classLoader) {
		if ( providedClassLoaders == null ) {
			providedClassLoaders = new ArrayList<ClassLoader>();
		}
		providedClassLoaders.add( classLoader );
		return this;
	}



	/**
	 * Compute aggregated expression in the context of each entity instance separately. Useful for retrieving latest
	 * revisions of all entities of a particular type.<br/>
	 * Implementation note: Correlates subquery with the outer query by entity id.
	 * @return this (for method chaining).
	 */
	public AggregatedAuditExpression computeAggregationInInstanceContext() {
		correlate = true;
		return this;
	}



	
	public MetadataBuilder applyScanner(Scanner scanner) {
		this.options.scannerSetting = scanner;
		return this;
	}



	
	public MetadataBuilder applyPhysicalNamingStrategy(PhysicalNamingStrategy namingStrategy) {
		this.options.physicalNamingStrategy = namingStrategy;
		return this;
	}



	
	public MetadataBuilder enableGlobalNationalizedCharacterDataSupport(boolean enabled) {
		options.useNationalizedCharacterData = enabled;
		return this;
	}



	
	public MetadataBuilder applyTempClassLoader(ClassLoader tempClassLoader) {
		options.tempClassLoader = tempClassLoader;
		return this;
	}



	
	public MetadataBuilder applyImplicitSchemaName(String implicitSchemaName) {
		options.mappingDefaults.implicitSchemaName = implicitSchemaName;
		return this;
	}



	
	public MetadataBuilder applyScanEnvironment(ScanEnvironment scanEnvironment) {
		this.options.scanEnvironment = scanEnvironment;
		return this;
	}



	
	public MetadataBuilder applyImplicitNamingStrategy(ImplicitNamingStrategy namingStrategy) {
		this.options.implicitNamingStrategy = namingStrategy;
		return this;
	}



	
	public MetadataBuilder applySharedCacheMode(SharedCacheMode sharedCacheMode) {
		this.options.sharedCacheMode = sharedCacheMode;
		return this;
	}



	
	public MetadataBuilder applyScanOptions(ScanOptions scanOptions) {
		this.options.scanOptions = scanOptions;
		return this;
	}



	
	public MetadataBuilder applyCacheRegionDefinition(CacheRegionDefinition cacheRegionDefinition) {
		if ( options.cacheRegionDefinitions == null ) {
			options.cacheRegionDefinitions = new ArrayList<CacheRegionDefinition>();
		}
		options.cacheRegionDefinitions.add( cacheRegionDefinition );
		return this;
	}



	
	public MetadataBuilder applyAccessType(AccessType implicitCacheAccessType) {
		this.options.mappingDefaults.implicitCacheAccessType = implicitCacheAccessType;
		return this;
	}



	
	public MetadataBuilder applyImplicitCatalogName(String implicitCatalogName) {
		options.mappingDefaults.implicitCatalogName = implicitCatalogName;
		return this;
	}



	
	public MetadataBuilder applyTypes(TypeContributor typeContributor) {
		typeContributor.contribute( this, options.serviceRegistry );
		return this;
	}



	
	public MetadataBuilder enableImplicitDiscriminatorsForJoinedSubclassSupport(boolean supported) {
		options.implicitDiscriminatorsForJoinedInheritanceSupported = supported;
		return this;
	}



	
	public MetadataBuilder applyArchiveDescriptorFactory(ArchiveDescriptorFactory factory) {
		this.options.archiveDescriptorFactory = factory;
		return this;
	}



	
	public MetadataBuilder enableExplicitDiscriminatorsForJoinedSubclassSupport(boolean supported) {
		options.explicitDiscriminatorsForJoinedInheritanceSupported = supported;
		return this;
	}



	
	public MetadataBuilder enableImplicitForcingOfDiscriminatorsInSelect(boolean supported) {
		options.implicitlyForceDiscriminatorInSelect = supported;
		return this;
	}



	
	public MetadataBuilder applyIndexView(IndexView jandexView) {
		this.options.jandexView = jandexView;
		return this;
	}



	/**
	 * Should the value returned by {@link #getCurrentTimestampSelectString}
	 * be treated as callable.  Typically this indicates that JDBC escape
	 * syntax is being used...
	 *
	 * @return True if the {@link #getCurrentTimestampSelectString} return
	 *         is callable; false otherwise.
	 */
	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}



	protected SessionFactoryImplementor delegate() {
		return delegate;
	}



	protected boolean doConnectionsFromProviderHaveAutoCommitDisabled() {
		return false;
	}



	protected SessionBuilder delegate() {
		return delegate;
	}



	/**
	 * Called from {@link #wrapWithQueryString(String)} when we really need to generate a new QueryException
	 * (or subclass).
	 * <p/>
	 * NOTE : implementors should take care to use {@link #getOriginalMessage()} for the message, not
	 * {@link #getMessage()}
	 *
	 * @param queryString The query string
	 *
	 * @return The generated QueryException (or subclass)
	 *
	 * @see #getOriginalMessage()
	 */
	protected QueryException generateQueryException(String queryString) {
		return new QueryException( getOriginalMessage(), queryString, this );
	}



	
	public void prepareServices(StandardServiceRegistryBuilder serviceRegistryBuilder) {
		boolean isSecurityEnabled = serviceRegistryBuilder.getSettings().containsKey( AvailableSettings.JACC_ENABLED );
		final JaccService jaccService = isSecurityEnabled ? new StandardJaccServiceImpl() : new DisabledJaccServiceImpl();
		serviceRegistryBuilder.addService( JaccService.class, jaccService );
	}



	public SharedSessionBuilder delegate() {
		return delegate;
	}



  public boolean useExpLookupTable()
  {
    return useExpLookupTable;
  }



  public boolean useHDFSExpLookupTable()
  {
    return useHDFSExpLookupTable;
  }



	
	public void recordValue(String name, double value, double alpha) {
		RichGauge gauge = getOrCreate(name);
		setRichGaugeValue(gauge, value, alpha);
	}



	/**
	 * Return the module allocation path for the given container id.
	 *
	 * @param id container id
	 * @return path for module allocations for the container
	 */
	private String containerAllocationPath(String id) {
		return Paths.build(Paths.MODULE_DEPLOYMENTS, Paths.ALLOCATED, id);
	}



	protected StreamDefinition createDefinition(String name, String definition) {
		return new StreamDefinition(name, definition);
	}



	
	protected StreamDefinitionResource instantiateResource(StreamDefinition entity) {
		return new StreamDefinitionResource(entity.getName(), entity.getDefinition());
	}



	
	public String getProviderName() {
		return "XD Shell";
	}



	protected JobDefinition createDefinition(String name, String definition) {
		return new JobDefinition(name, definition);
	}



	public PdxInstance toObject(String json) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(json);
		} catch (JSONException e) {
			throw new MessageTransformationException(e.getMessage());
		}
		return JSONFormatter.fromJSON(jsonObject.toString());
	}



	
	public void recordValue(String name, long value) {
		Gauge gauge = getOrCreate(name);
		setGaugeValue(gauge, value);
	}



	
	public String getProviderName() {
		return "prompt";
	}



	
	public void recordValue(String name, long value) {
		getValueOperations().set(getMetricKey(name), value);
	}



    /**
     * Close a path.
     * 
     * <p>Dependent on the lineWidth and whether or not there is a background
     * to be generated there are different commands to be used for closing a path.
     * 
     * @param lineWidth the line width of the path.
     * @param hasBackground shall there be a background color.
     * @throws IOException if an IO error occurs while writing to the stream.
     */
    public void drawShape(float lineWidth, boolean hasBackground) throws IOException
    {
        if (lineWidth < 1e-6) {
            writeOperator("n");
        }
        else
        {
            if (!hasBackground)
            {
                stroke();
            } else
            {
                fillAndStroke();
            }
        }
    }



    /**
     * Fills and then strokes the path.
     *
     * @param windingRule The winding rule this path will use.
     *
     * @throws IOException If there is an IO error while filling the path.
     */
    public void fillAndStrokePath(int windingRule) throws IOException
    {
        // TODO can we avoid cloning the path?
        GeneralPath path = (GeneralPath)linePath.clone();
        fillPath(windingRule);
        linePath = path;
        strokePath();
    }



    
    protected boolean isDataEmpty()
    {
        return patchList.isEmpty();
    }    



    
    protected boolean isDataEmpty()
    {
        return triangleList.isEmpty();
    }



	/**
	 * Get the bridge defined field of the specific class.
	 *
	 * @param bridgeDefineFieldClass the type of the object containing the properties of the field
	 * @return the object containing the properties of the field
	 */
	public <T> T getBridgeDefinedField(Class<T> bridgeDefineFieldClass) {
		return bridgeDefineFieldClass.cast( extra.get( bridgeDefineFieldClass ) );
	}



	public static String stripSpatialFieldSuffix(String fieldName) {
		if ( !isSpatialField( fieldName ) ) {
			throw new AssertionFailure( "The field " + fieldName + " is not a spatial field." );
		}
		return fieldName.substring( 0, fieldName.indexOf( SPATIAL_FIELD_SUFFIX ) );
	}



	public boolean hasZeroCountsIncluded() {
		return includeZeroCounts;
	}



	
	public MoreLikeThisContext favorSignificantTermsWithFactor(float factor) {
		moreLikeThisContext.setBoostTerms( true );
		moreLikeThisContext.setTermBoostFactor( factor );
		return this;
	}



	protected List doHibernateSearchList() {
		hSearchQuery.getTimeoutManager().start();
		final List<EntityInfo> entityInfos = hSearchQuery.queryEntityInfos();
		Loader loader = getLoader();
		List list = loader.load( entityInfos );
		//no need to timeoutManager.isTimedOut from this point, we don't do anything intensive
		if ( resultTransformer == null || loader instanceof ProjectionLoader ) {
			//stay consistent with transformTuple which can only be executed during a projection
			//nothing to do
		}
		else {
			list = resultTransformer.transformList( list );
		}
		hSearchQuery.getTimeoutManager().stop();
		return list;
	}



	public int doGetResultSize() {
		if ( getLoader().isSizeSafe() ) {
			return hSearchQuery.queryResultSize();
		}
		else {
			throw log.cannotGetResultSizeWithCriteriaAndRestriction( criteria.toString() );
		}
	}



	
	public Iterator<LuceneWork> iterator() {
		return workList.iterator();
	}



    
    @SuppressWarnings("rawtypes")
    final List<AccessDecisionVoter> getDecisionVoters() {
        List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
        WebExpressionVoter expressionVoter = new WebExpressionVoter();
        expressionVoter.setExpressionHandler(expressionHandler);
        decisionVoters.add(expressionVoter);
        return decisionVoters;
    }



    /**
     * Creates the default {@link AccessDecisionVoter} instances used if an
     * {@link AccessDecisionManager} was not specified using
     * {@link #accessDecisionManager(AccessDecisionManager)}.
     */
    
    @SuppressWarnings("rawtypes")
    final List<AccessDecisionVoter> getDecisionVoters() {
        List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(new AuthenticatedVoter());
        return decisionVoters;
    }



    protected DatabasePopulator getDatabasePopulator() {
        ResourceDatabasePopulator dbp = new ResourceDatabasePopulator();
        dbp.setScripts(initScripts.toArray(new Resource[initScripts.size()]));
        return dbp;
    }



  public boolean getDirection() {
    return direction;
  }



    
    public NodeBuilder builder() {
        return new MemoryNodeBuilder(this);
    }



    /**
     * Checks whether this exception is of the given type.
     *
     * @param type type name
     * @return {@code true} iff this exception is of the given type
     */
    public boolean isOfType(String type) {
        return this.type.equals(type);
    }



    private static PlanResult getPlanResult(IndexPlan plan) {
        return (PlanResult) plan.getAttribute(ATTR_PLAN_RESULT);
    }



     @Nonnull
    public NodeBuilder setChildNode(@Nonnull String name) {
        throw unsupported();
    }



    /**
     * Configures whether SSL connections should be used.
     * The default is {@value #PARAM_USE_SSL_DEFAULT}.
     *
     * @return {@code true} if SSL should be used.
     */
    public boolean useSSL() {
        return useSSL;
    }



    /**
     * Flush the id of the current head to the journal after a call to {@code
     * persisted}. This method does nothing and returns immediately if called
     * concurrently and a call is already in progress.
     *
     * @param flusher call back for upstream dependencies to ensure the current
     *                head state is actually persisted before its id is written
     *                to the head state.
     */
    void tryFlush(Flusher flusher) throws IOException {
        if (head.get() == null) {
            LOG.debug("No head available, skipping flush");
            return;
        }
        if (journalFileLock.tryLock()) {
            try {
                doFlush(flusher);
            } finally {
                journalFileLock.unlock();
            }
        } else {
            LOG.debug("Unable to lock the journal, skipping flush");
        }
    }



    /**
     * Copy history filtering versions using passed date and returns {@code
     * true} if the history has been copied.
     * 
     * @param versionableUuid
     *            Name of the version history node
     * @param minDate
     *            Only versions older than this date will be copied
     * @return {@code true} if at least one version has been copied
     */
    public boolean copyVersionHistory(String versionableUuid, Calendar minDate) {
        final String versionHistoryPath = getRelativeVersionHistoryPath(versionableUuid);
        final NodeState sourceVersionHistory = getVersionHistoryNodeState(sourceVersionStorage, versionableUuid);
        final Calendar lastModified = getVersionHistoryLastModified(sourceVersionHistory);

        if (sourceVersionHistory.exists() && (lastModified.after(minDate) || minDate.getTimeInMillis() == 0)) {
            NodeStateCopier.builder()
                    .include(versionHistoryPath)
                    .merge(VERSION_STORE_PATH)
                    .copy(sourceVersionStorage, targetVersionStorage);
            return true;
        }
        return false;
    }



    private long readPreviousTailCleanupSize() {
        return gcJournal.read().getRepoSize();
    }



    /**
     * Creates a new TAR writer with a higher index number, reopens the previous
     * TAR writer as a TAR reader, and adds the TAR reader to the linked list.
     * <p>
     * This method must be invoked while holding {@link #lock} in write mode,
     * because it modifies the references {@link #writer} and {@link #readers}.
     *
     * @throws IOException If an error occurs while operating on the TAR readers
     *                     or the TAR writer.
     */
    private void internalNewWriter() throws IOException {
        TarWriter newWriter = writer.createNextGeneration();
        if (newWriter == writer) {
            return;
        }
        readers = new Node(TarReader.open(writer.getFile(), memoryMapping, ioMonitor), readers);
        writer = newWriter;
    }



    /**
     * Completes the process by deleting the files.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void close() throws IOException {
        if (!getGarbage().exists() ||
                FileUtils.sizeOf(getGarbage()) == 0) {
            FileUtils.deleteDirectory(home);
        }
    }



    private static long doGetEstimatedNodeCount(NodeState root, String path, boolean max) {
        // check if there is a property in the node itself
        // (for property index nodes)
        NodeState s = child(root,
                PathUtils.elements(path));
        if (s == null || !s.exists()) {
            // node not found
            return 0;
        }
        if (!max) {
            long syncCount = ApproximateCounter.getCountSync(s);
            if (syncCount != -1) {
                return syncCount;
            }
        }
        if (COUNT_HASH) {
            return getCombinedCount(root, path, s, max);
        }
        return getEstimatedNodeCountOld(root, s, path, max);
    }



    /**
     * Compute a set of sub-constraints that could be used for composing UNION
     * statements. For example in case of "c=1 or c=2", it will return to the
     * caller {@code [c=1, c=2]}. Those can be later on used for re-composing
     * conditions.
     * <p>
     * If it is not possible to convert to a union, it must return an empty set.
     * <p>
     * The default implementation in {@link ConstraintImpl#convertToUnion()}
     * always return an empty set.
     * 
     * @return the set of union constraints, if available, or an empty set if
     *         conversion is not possible
     */
    @Nonnull
    public Set<ConstraintImpl> convertToUnion() {
        return Collections.emptySet();
    }



    
    public Query buildAlternativeQuery() {
        return this;
    }



    
    public void executeDeleteFilesManually() {
        this.cleanCommitLogService.excuteDeleteFilesManualy();
    }



	private OAuth2AccessToken convertAccessToken(String tokenValue) {
		return jwtTokenEnhancer.extractAccessToken(tokenValue, jwtTokenEnhancer.decode(tokenValue));
	}



  private static boolean isArrayValue(ExpressionTree argument) {
    if (!(argument instanceof AssignmentTree)) {
      return false;
    }
    ExpressionTree expression = ((AssignmentTree) argument).getExpression();
    return expression instanceof NewArrayTree && ((NewArrayTree) expression).getType() == null;
  }



  
  protected void processUpdate(PathsUpdate update) {
    try {
      // push to ZK in order to keep the metastore local cache in sync
      pluginCacheSync.handleCacheUpdate(update);

      // notify Sentry. Note that Sentry service already has a cache
      // sync mechanism to replicate this update to all other Sentry servers
      notifySentry(update);
    } catch (SentryPluginException e) {
      LOGGER.error("Error pushing update to cache", e);
    }
  }



  public TPermissionsUpdate toThrift() {
    return tPermUpdate;
  }



  protected void processUpdate(PathsUpdate update) {
    applyLocal(update);
    notifySentry(update);
  }



  public TPathsUpdate toThrift() {
    return tPathsUpdate;
  }



  
  public boolean hasSuccessfullyUnregistered() {
    // bogus - Not Required
    return true;
  }



  public String getPath() {
    return this.path;
  }



  public String getHost() {
    return this.host;
  }



  /**
   * Set block keys, only to be used in slave mode
   */
  public synchronized void addKeys(ExportedBlockKeys exportedKeys)
      throws IOException {
    if (isMaster || exportedKeys == null)
      return;
    LOG.info("Setting block keys");
    removeExpiredKeys();
    this.currentKey = exportedKeys.getCurrentKey();
    BlockKey[] receivedKeys = exportedKeys.getAllKeys();
    for (int i = 0; i < receivedKeys.length; i++) {
      if (receivedKeys[i] == null)
        continue;
      this.allKeys.put(receivedKeys[i].getKeyId(), receivedKeys[i]);
    }
  }



  private static boolean isNodeExists(Code code) {
    return (code == Code.NODEEXISTS);
  }



  private static boolean isNodeDoesNotExist(Code code) {
    return (code == Code.NONODE);
  }



  private static boolean isSuccess(Code code) {
    return (code == Code.OK);
  }



  
  protected void setFieldsFromProperties(Properties props, StorageDirectory sd)
      throws IOException {
    setLayoutVersion(props, sd);
    setNamespaceID(props, sd);
    setcTime(props, sd);
    
    String sbpid = props.getProperty("blockpoolID");
    setBlockPoolID(sd.getRoot(), sbpid);
  }



  
  public void setServiceData(final Map<String, ByteBuffer> serviceData) {
    if (serviceData == null)
      return;
    initServiceData();
    this.serviceData.putAll(serviceData);
  }



  private void unsyncSetGraceSleepPeriod(final long gracePeriod) {
    if (gracePeriod < 100L) {
      throw new HadoopIllegalArgumentException(gracePeriod
          + " = gracePeriod < 100ms is too small.");
    }
    this.gracePeriod = gracePeriod;
    final long half = gracePeriod/2;
    this.sleepPeriod = half < LEASE_RENEWER_SLEEP_DEFAULT?
        half: LEASE_RENEWER_SLEEP_DEFAULT;
  }



  
  public BatchedEntries<CachePoolEntry> listCachePools(String prevKey)
      throws IOException {
    try {
      return new BatchedCachePoolEntries(
        rpcProxy.listCachePools(null,
          ListCachePoolsRequestProto.newBuilder().
            setPrevPoolName(prevKey).build()));
    } catch (ServiceException e) {
      throw ProtobufHelper.getRemoteException(e);
    }
  }



  private synchronized void rollVerificationLogs() {
    if (verificationLog != null) {
      try {
        verificationLog.logs.roll();
      } catch (IOException ex) {
        LOG.warn("Received exception: ", ex);
        verificationLog.close();
      }
    }
  }



  /**
   * Generate new storage ID. The format of this string can be changed
   * in the future without requiring that old storage IDs be updated.
   *
   * @return unique storage ID
   */
  public static String generateUuid() {
    return "DS-" + UUID.randomUUID();
  }



  protected boolean keepTaskFiles(JobConf conf) {
    return (conf.getKeepTaskFilesPattern() != null || conf
        .getKeepFailedTaskFiles());
  }



  /**
   * Return the previous block on the block list for the datanode at
   * position index. Set the previous block on the list to "to".
   *
   * @param index - the datanode index
   * @param to - block to be set to previous on the list of blocks
   * @return current previous block on the list of blocks
   */
  private BlockInfo setPrevious(int index, BlockInfo to) {
	assert this.triplets != null : "BlockInfo is not initialized";
	assert index >= 0 && index*3+1 < triplets.length : "Index is out of bound";
    BlockInfo info = (BlockInfo)triplets[index*3+1];
    triplets[index*3+1] = to;
    return info;
  }



  /**
   * Return the next block on the block list for the datanode at
   * position index. Set the next block on the list to "to".
   *
   * @param index - the datanode index
   * @param to - block to be set to next on the list of blocks
   *    * @return current next block on the list of blocks
   */
  private BlockInfo setNext(int index, BlockInfo to) {
	assert this.triplets != null : "BlockInfo is not initialized";
	assert index >= 0 && index*3+2 < triplets.length : "Index is out of bound";
    BlockInfo info = (BlockInfo)triplets[index*3+2];
    triplets[index*3+2] = to;
    return info;
  }



  void snapshot(MetricsRecordBuilder rb, boolean all) {
    registry.snapshot(rb, all);
  }



  synchronized boolean shortCircuitForbidden() {
    return locatedBlocks.isUnderConstruction();
  }



  protected boolean qualifiesForBlacklisting() {
    return blacklistingEnabled && (numFailedTAs >= maxTaskFailuresPerNode);
  }



  
  public void shutdown() {
    stopRpcServer();
  }



  
  public void start() {
    startRpcServer();
  }



  
  public void start() {
    asyncDelegateRequestThread.start();
  }



  
  public void start() throws Exception {
    eventHandlingThread =
        new Thread(new TezSubTaskRunner(), "LocalContainerLauncher-SubTaskRunner");
    eventHandlingThread.start();
  }



  
  public synchronized void dagComplete() {
    for (HeldContainer heldContainer : heldContainers.values()) {
      heldContainer.resetLocalityMatchLevel();
    }
    synchronized(delayedContainerManager) {
      delayedContainerManager.notify();
    }
  }



	/**
	 * This method is expected to be called ONLY to process the results for multiple-columns in a table.
	 * To ensure this, RangerHiveAuthorizer should call isAccessAllowed(Collection<requests>) only for this condition
	 */
	
	public void processResults(Collection<RangerAccessResult> results) {
		List<AuthzAuditEvent> auditEvents = createAuditEvents(results);
		for(AuthzAuditEvent auditEvent : auditEvents) {
			addAuthzAuditEvent(auditEvent);
		}
	}



  private Set<W> windowsThatAreOpen(Collection<W> windows) {
    Set<W> result = new HashSet<>();
    for (W window : windows) {
      ReduceFn<K, InputT, OutputT, W>.Context directContext = contextFactory.base(
          window, StateStyle.DIRECT);
      if (!triggerRunner.isClosed(directContext.state())) {
        result.add(window);
      }
    }
    return result;
  }



  
  public java.util.List<? extends UnboundedSource<T, CheckpointMark>> split(
      int desiredNumSplits, PipelineOptions options) throws Exception {
    return Collections.singletonList(this);
  }



  public int getFieldCount(){
    return fieldNames.size();
  }



  @SuppressWarnings("unchecked")
  
  public void process(
      CommittedBundle<?> bundle,
      AppliedPTransform<?, ?, ?> consumer,
      CompletionCallback onComplete) {
    evaluateBundle(consumer, bundle, onComplete);
  }



  /**
   * Transforms a SQL query into a {@link PTransform} representing an equivalent execution plan.
   *
   * <p>This is a simplified form of {@link #queryMulti(String)} where the query must reference
   * a single input table.
   *
   * <p>Make sure to query it from a static table name <em>PCOLLECTION</em>.
   */
  public static SimpleQueryTransform query(String sqlQuery) {
    return new SimpleQueryTransform(sqlQuery);
  }



  public int getFieldCount() {
    return dataValues.size();
  }



  
  public java.util.List<? extends UnboundedSource<T, CheckpointMark>> split(
      int desiredNumSplits, PipelineOptions options) throws Exception {
    return Collections.singletonList(this);
  }



  
  public PCollection<T> expand(PCollection<T> input) {
    return input.apply(
        "AddTimestamps", ParDo.of(new AddTimestampsDoFn<T>(fn, allowedTimestampSkew)));
  }



  
  public PCollection<V> expand(PCollection<? extends KV<?, V>> in) {
    return
        in.apply("Values", MapElements.via(new SimpleFunction<KV<?, V>, V>() {
          
          public V apply(KV<?, V> kv) {
            return kv.getValue();
          }
        }));
  }



  private static String extractFilename(ResourceId input) {
    if (input.isDirectory()) {
      return "";
    } else {
      return firstNonNull(input.getFilename(), "");
    }
  }



  
  public List<? extends UnboundedSource<String, CheckpointMarkT>> split(
      int desiredNumSplits,
      PipelineOptions options) throws Exception {
    return Collections.<UnboundedSource<String, CheckpointMarkT>>singletonList(this);
  }



  
  public PCollection<KV<V, K>> expand(PCollection<KV<K, V>> in) {
    return
        in.apply("KvSwap", MapElements.via(new SimpleFunction<KV<K, V>, KV<V, K>>() {
          
          public KV<V, K> apply(KV<K, V> kv) {
            return KV.of(kv.getValue(), kv.getKey());
          }
        }));
  }



  
  public PCollection<KV<K, Iterable<V>>> expand(PCollection<KV<K, V>> input) {
    WindowingStrategy<?, ?> windowingStrategy = input.getWindowingStrategy();

    return input
        // Group by just the key.
        // Combiner lifting will not happen regardless of the disallowCombinerLifting value.
        // There will be no combiners right after the GroupByKeyOnly because of the two ParDos
        // introduced in here.
        .apply(new GroupByKeyOnly<K, V>())

        // Sort each key's values by timestamp. GroupAlsoByWindow requires
        // its input to be sorted by timestamp.
        .apply(new SortValuesByTimestamp<K, V>())

        // Group each key's values by window, merging windows as needed.
        .apply(new GroupAlsoByWindow<K, V>(windowingStrategy))

        // And update the windowing strategy as appropriate.
        .setWindowingStrategyInternal(
            gbkTransform.updateWindowingStrategy(windowingStrategy));
  }



  
  public PCollection<K> expand(PCollection<? extends KV<K, ?>> in) {
    return
        in.apply("Keys", MapElements.via(new SimpleFunction<KV<K, ?>, K>() {
          
          public K apply(KV<K, ?> kv) {
            return kv.getKey();
          }
        }));
  }



  private ClientUpgradeRequest getConnectionRequest(String token) {
    ClientUpgradeRequest request = new ClientUpgradeRequest();
    request.setCookies(Lists.newArrayList(new HttpCookie(ZeppelinHubRepo.TOKEN_HEADER, token)));
    return request;
  }



  public String toJson() {
    return gson.toJson(this, ZeppelinhubMessage.class);
  }



  private void runCondaHelp(InterpreterOutput out) {
    try {
      out.setType(InterpreterResult.Type.HTML);
      out.writeResource("output_templates/conda_usage.html");
    } catch (IOException e) {
      logger.error("Can't print usage", e);
    }
  }



  private boolean hasUser() {
    return this.user != null;
  }



  public String toJson() {
    return gson.toJson(this);
  }



	/**
	 * Creates a default {@link JedisClientConfiguration}.
	 * <dl>
	 * <dt>SSL enabled</dt>
	 * <dd>no</dd>
	 * <dt>Pooling enabled</dt>
	 * <dd>no</dd>
	 * <dt>Client Name</dt>
	 * <dd>[not set]</dd>
	 * <dt>Read Timeout</dt>
	 * <dd>2000 msec</dd>
	 * <dt>Connect Timeout</dt>
	 * <dd>2000 msec</dd>
	 * </dl>
	 *
	 * @return a {@link JedisClientConfiguration} with defaults.
	 */
	static JedisClientConfiguration defaultConfiguration() {
		return builder().build();
	}



	/**
	 * Create a new {@link RedisCacheManager} with defaults applied.
	 * <dl>
	 * <dt>locking</dt>
	 * <dd>disabled</dd>
	 * <dt>cache configuration</dt>
	 * <dd>{@link RedisCacheConfiguration#defaultCacheConfig()}</dd>
	 * <dt>initial caches</dt>
	 * <dd>none</dd>
	 * <dt>transaction aware</dt>
	 * <dd>no</dd>
	 * </dl>
	 *
	 * @param connectionFactory must not be {@literal null}.
	 * @return new instance of {@link RedisCacheManager}.
	 */
	public static RedisCacheManager create(RedisConnectionFactory connectionFactory) {

		Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

		return new RedisCacheManager(new DefaultRedisCacheWriter(connectionFactory),
				RedisCacheConfiguration.defaultCacheConfig());
	}



	
	public boolean isUseSsl() {
		return useSsl;
	}



	
	public boolean isUseSsl() {
		return useSsl;
	}



	
	public boolean isUsePooling() {
		return usePooling;
	}



	
	public List<Point> position(K key, M... members) {
		byte[] rawKey = rawKey(key);
		byte[][] rawMembers = rawValues(members);

		return execute(connection -> connection.geoPos(rawKey, rawMembers), true);
	}



	
	public Long add(K key, Map<M, Point> memberCoordinateMap) {

		byte[] rawKey = rawKey(key);
		Map<byte[], Point> rawMemberCoordinateMap = new HashMap<>();

		for (M member : memberCoordinateMap.keySet()) {
			byte[] rawMember = rawValue(member);
			rawMemberCoordinateMap.put(rawMember, memberCoordinateMap.get(member));
		}

		return execute(connection -> connection.geoAdd(rawKey, rawMemberCoordinateMap), true);
	}



	
	public GeoResults<GeoLocation<M>> radius(K key, Circle within, GeoRadiusCommandArgs args) {

		byte[] rawKey = rawKey(key);

		GeoResults<GeoLocation<byte[]>> raw = execute(connection -> connection.geoRadius(rawKey, within, args), true);

		return deserializeGeoResults(raw);
	}



	
	public Long add(K key, Point point, M member) {

		byte[] rawKey = rawKey(key);
		byte[] rawMember = rawValue(member);

		return execute(connection -> connection.geoAdd(rawKey, point, rawMember), true);
	}



	
	public GeoResults<GeoLocation<M>> radius(K key, Circle within) {

		byte[] rawKey = rawKey(key);

		GeoResults<GeoLocation<byte[]>> raw = execute(connection -> connection.geoRadius(rawKey, within), true);

		return deserializeGeoResults(raw);
	}



	
	public List<String> hash(K key, M... members) {

		byte[] rawKey = rawKey(key);
		byte[][] rawMembers = rawValues(members);

		return execute(connection -> connection.geoHash(rawKey, rawMembers), true);
	}



	
	public GeoResults<GeoLocation<M>> radius(K key, M member, double radius) {

		byte[] rawKey = rawKey(key);
		byte[] rawMember = rawValue(member);
		GeoResults<GeoLocation<byte[]>> raw = execute(connection -> connection.geoRadiusByMember(rawKey, rawMember, radius),
				true);

		return deserializeGeoResults(raw);
	}



	
	public Distance distance(K key, M member1, M member2) {

		byte[] rawKey = rawKey(key);
		byte[] rawMember1 = rawValue(member1);
		byte[] rawMember2 = rawValue(member2);

		return execute(connection -> connection.geoDist(rawKey, rawMember1, rawMember2), true);
	}



	
	public Distance distance(K key, M member1, M member2, Metric metric) {

		byte[] rawKey = rawKey(key);
		byte[] rawMember1 = rawValue(member1);
		byte[] rawMember2 = rawValue(member2);

		return execute(connection -> connection.geoDist(rawKey, rawMember1, rawMember2, metric), true);
	}



	
	public GeoResults<GeoLocation<M>> radius(K key, M member, Distance distance, GeoRadiusCommandArgs param) {

		byte[] rawKey = rawKey(key);
		byte[] rawMember = rawValue(member);

		GeoResults<GeoLocation<byte[]>> raw = execute(
				connection -> connection.geoRadiusByMember(rawKey, rawMember, distance, param), true);

		return deserializeGeoResults(raw);
	}



	
	public Long remove(K key, M... members) {

		byte[] rawKey = rawKey(key);
		byte[][] rawMembers = rawValues(members);
		return execute(connection -> connection.zRem(rawKey, rawMembers), true);
	}



	
	public GeoResults<GeoLocation<M>> radius(K key, M member, Distance distance) {

		byte[] rawKey = rawKey(key);
		byte[] rawMember = rawValue(member);

		GeoResults<GeoLocation<byte[]>> raw = execute(
				connection -> connection.geoRadiusByMember(rawKey, rawMember, distance), true);

		return deserializeGeoResults(raw);
	}



	/**
	 * Updates indexes by first removing key from existing one and then persisting new index data.
	 *
	 * @param key must not be {@literal null}.
	 * @param indexValues can be {@literal null}.
	 */
	public void deleteAndUpdateIndexes(Object key, Iterable<IndexedData> indexValues) {
		createOrUpdateIndexes(key, indexValues, IndexWriteMode.UPDATE);
	}



	
	public Mono<Distance> distance(K key, V member1, V member2, Metric metric) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member1, "Member 1 must not be null!");
		Assert.notNull(member2, "Member 2 must not be null!");
		Assert.notNull(metric, "Metric must not be null!");

		return createMono(connection -> connection.geoDist(rawKey(key), rawValue(member1), rawValue(member2), metric));
	}



	
	public Mono<Point> position(K key, V member) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member, "Member must not be null!");

		return createMono(connection -> connection.geoPos(rawKey(key), rawValue(member)));
	}



	
	@SafeVarargs
	public final Mono<List<Point>> position(K key, V... members) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notEmpty(members, "Members must not be null or empty!");
		Assert.noNullElements(members, "Members must not contain null elements!");

		return createMono(connection -> Flux.fromArray(members) //
				.map(this::rawValue) //
				.collectList() //
				.flatMap(serialized -> connection.geoPos(rawKey(key), serialized)));
	}



	
	public Flux<GeoResult<GeoLocation<V>>> radius(K key, Circle within, GeoRadiusCommandArgs args) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(within, "Circle must not be null!");
		Assert.notNull(args, "GeoRadiusCommandArgs must not be null!");

		return createFlux(connection -> connection.geoRadius(rawKey(key), within, args) //
				.map(this::readGeoResult));
	}



	
	public Mono<String> hash(K key, V member) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member, "Member must not be null!");

		return createMono(connection -> connection.geoHash(rawKey(key), rawValue(member)));
	}



	
	public Flux<GeoResult<GeoLocation<V>>> radius(K key, V member, Distance distance) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member, "Member must not be null!");
		Assert.notNull(distance, "Distance must not be null!");

		return createFlux(connection -> connection.geoRadiusByMember(rawKey(key), rawValue(member), distance) //
				.map(this::readGeoResult));
	}



	
	@SafeVarargs
	public final Mono<List<String>> hash(K key, V... members) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notEmpty(members, "Members must not be null or empty!");
		Assert.noNullElements(members, "Members must not contain null elements!");

		return createMono(connection -> Flux.fromArray(members) //
				.map(this::rawValue) //
				.collectList() //
				.flatMap(serialized -> connection.geoHash(rawKey(key), serialized)));
	}



	
	public Mono<Long> add(K key, Iterable<GeoLocation<V>> geoLocations) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(geoLocations, "GeoLocations must not be null!");

		return createMono(connection -> {

			Mono<List<GeoLocation<ByteBuffer>>> serializedList = Flux.fromIterable(geoLocations)
					.map(location -> new GeoLocation<>(rawValue(location.getName()), location.getPoint())).collectList();

			return serializedList.flatMap(list -> connection.geoAdd(rawKey(key), list));
		});
	}



	
	public Flux<GeoResult<GeoLocation<V>>> radius(K key, V member, double radius) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member, "Member must not be null!");

		return createFlux(connection -> connection.geoRadiusByMember(rawKey(key), rawValue(member), new Distance(radius)) //
				.map(this::readGeoResult));
	}



	
	public Flux<Long> add(K key, Publisher<? extends Collection<GeoLocation<V>>> locations) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(locations, "Locations must not be null!");

		return createFlux(connection -> Flux.from(locations)
				.map(locationList -> locationList.stream()
						.map(location -> new GeoLocation<>(rawValue(location.getName()), location.getPoint()))
						.collect(Collectors.toList()))
				.flatMap(list -> connection.geoAdd(rawKey(key), list)));
	}



	
	public Flux<GeoResult<GeoLocation<V>>> radius(K key, V member, Distance distance, GeoRadiusCommandArgs args) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member, "Member must not be null!");
		Assert.notNull(distance, "Distance must not be null!");
		Assert.notNull(args, "GeoRadiusCommandArgs must not be null!");

		return createFlux(connection -> connection.geoRadiusByMember(rawKey(key), rawValue(member), distance, args))
				.map(this::readGeoResult);
	}



	
	public Mono<Distance> distance(K key, V member1, V member2) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(member1, "Member 1 must not be null!");
		Assert.notNull(member2, "Member 2 must not be null!");

		return createMono(connection -> connection.geoDist(rawKey(key), rawValue(member1), rawValue(member2)));
	}



	
	public Mono<Long> add(K key, Map<V, Point> memberCoordinateMap) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(memberCoordinateMap, "MemberCoordinateMap must not be null!");

		return createMono(connection -> {

			Mono<List<GeoLocation<ByteBuffer>>> serializedList = Flux
					.fromIterable(() -> memberCoordinateMap.entrySet().iterator())
					.map(entry -> new GeoLocation<>(rawValue(entry.getKey()), entry.getValue())).collectList();

			return serializedList.flatMap(list -> connection.geoAdd(rawKey(key), list));
		});
	}



	
	public Mono<Long> add(K key, GeoLocation<V> location) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(location, "GeoLocation must not be null!");

		return createMono(connection -> connection.geoAdd(rawKey(key),
				new GeoLocation<>(rawValue(location.getName()), location.getPoint())));
	}



	
	public Mono<Long> add(K key, Point point, V member) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(point, "Point must not be null!");
		Assert.notNull(member, "Member must not be null!");

		return createMono(connection -> connection.geoAdd(rawKey(key), point, rawValue(member)));
	}



	
	public Flux<GeoResult<GeoLocation<V>>> radius(K key, Circle within) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notNull(within, "Circle must not be null!");

		return createFlux(connection -> connection.geoRadius(rawKey(key), within).map(this::readGeoResult));
	}



	
	@SafeVarargs
	public final Mono<Long> remove(K key, V... members) {

		Assert.notNull(key, "Key must not be null!");
		Assert.notEmpty(members, "Members must not be null or empty!");
		Assert.noNullElements(members, "Members must not contain null elements!");

		return template.createMono(connection -> Flux.fromArray(members) //
				.map(this::rawValue) //
				.collectList() //
				.flatMap(serialized -> connection.zSetCommands().zRem(rawKey(key), serialized)));
	}



	/**
	 * Creates a default {@link LettuceClientConfiguration} with:
	 * <dl>
	 * <dt>SSL</dt>
	 * <dd>no</dd>
	 * <dt>Peer Verification</dt>
	 * <dd>yes</dd>
	 * <dt>Start TLS</dt>
	 * <dd>no</dd>
	 * <dt>Client Options</dt>
	 * <dd>none</dd>
	 * <dt>Client Resources</dt>
	 * <dd>none</dd>
	 * <dt>Connect Timeout</dt>
	 * <dd>60 Seconds</dd>
	 * <dt>Shutdown Timeout</dt>
	 * <dd>2 Seconds</dd>
	 * </dl>
	 *
	 * @return a {@link LettuceClientConfiguration} with defaults.
	 */
	static LettuceClientConfiguration defaultConfiguration() {
		return builder().build();
	}



	/**
	 * Main configuration for the REST exporter.
	 */
	@Bean
	public RepositoryRestConfiguration repositoryRestConfiguration() {

		ProjectionDefinitionConfiguration configuration = new ProjectionDefinitionConfiguration();

		// Register projections found in packages
		for (Class<?> projection : getProjections(repositories())) {
			configuration.addProjection(projection);
		}

		RepositoryRestConfiguration config = new RepositoryRestConfiguration(configuration, metadataConfiguration(),
				enumTranslator());
		configurerDelegate.configureRepositoryRestConfiguration(config);

		return config;
	}



    public void removeListener(ConfTableListener listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }



    public void removeListener(SettingsEditorListener listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }



    public void removeListener(IvyXmlPathListener listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }



  
  public boolean isConnected()
  {
    if(service.getToken() == null)
      return false;
    else
      return true;
  }



  
  public boolean isConnected()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }



  
  public boolean isConnected()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }



  
  public boolean isConnected()
  {
    return connection != null;
  }



  
  public boolean isConnected() {
    // not applicable to hbase
    return false;
  }



  
  public String getKey(Integer tuple)
  {
    return "Key" + tuple;
  }



  
  public void putAll(Map<Object, Object> data)
  {
    cache.asMap().putAll(data);
  }



  
  public boolean isConnected() {
    return !client.isConnected();
  }



  
  public Iterable<Map.Entry<Window, T>> entries()
  {
    return map.entrySet();
  }



  
  public boolean isConnected()
  {
    try {
      mongoClient.getConnector().getDBPortPool(mongoClient.getAddress()).get().ensureOpen();
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  }



  
  public boolean isConnected()
  {
    return dbConnector == null;
  }



  
  public boolean isConnected()
  {
    return jedis != null && jedis.isConnected();
  }



  
  public boolean isConnected()
  {
    try {
      return !session.isClosed();
    }
    catch (DriverException ex) {
      throw new RuntimeException("closing database resource", ex);
    }
  }



  
  public boolean isConnected()
  {
    return open;
  }



  private static String createTmpFilePath(String filePath)
  {
    return filePath + '.' + System.currentTimeMillis() + TMP_EXTENSION;
  }



  
  public void put(Object key, Object value)
  {
    cache.put(key, value);
  }



  
  public boolean isConnected() {
    // Not applicable for accumulo
    return false;
  }



  public Boolean getMergeMetadata() {
    return mergeMetadata;
  }



  public Boolean getReadMetadata() {
    return readMetadata;
  }



    
    public Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object[] args = {property};
        return method == null ? null : method.invoke(obj, args);
    }



    
    public Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
        return method == null ? null : method.invoke(obj, (Object[]) null);
    }



    /**
     * Sets the charset to use.
     *
     * @param arg the charset
     * @return this builder
     */
    public JexlBuilder charset(Charset arg) {
        this.charset = arg;
        return this;
    }



    
    public Object invoke(Object obj, Object value) throws IllegalAccessException, InvocationTargetException {
        Object[] pargs = {property, value};
        if (method != null) {
            method.invoke(obj, pargs);
        }
        return value;
    }



    
    public Object invoke(final Object obj, Object value) throws IllegalAccessException, InvocationTargetException {
        @SuppressWarnings("unchecked") // ctor only allows Map instances - see discover() method
        final Map<Object,Object> map = ((Map<Object, Object>) obj);
        map.put(property, value);
        return value;
    }



    
    public Object invoke(Object o)
        throws IllegalAccessException, InvocationTargetException {
        return method == null ? null : method.invoke(o, (Object[]) null);
    }



    /**
     * Performs a logical not.
     * @param val the operand
     * @return !val
     */
    public Object not(Object val) {
        return toBoolean(val) ? Boolean.FALSE : Boolean.TRUE;
    }



    /**
     * Triggered when method, function or constructor invocation fails with an exception.
     * @param node       the node triggering the exception
     * @param methodName the method/function name
     * @param xany       the cause
     * @return a JexlException that will be thrown
     */
    protected JexlException invocationException(JexlNode node, String methodName, Exception xany) {
        Throwable cause = xany.getCause();
        if (cause instanceof JexlException) {
            return (JexlException) cause;
        }
        if (cause instanceof InterruptedException) {
            cancelled = true;
            return new JexlException.Cancel(node);
        }
        return new JexlException(node, methodName, xany);
    }



    
    public Object invoke(final Object obj) {
        @SuppressWarnings("unchecked") // ctor only allows Map instances - see discover() method
        final Map<Object, ?> map = (Map<Object, ?>) obj;
        return map.get(property);
    }



    /**
     * @return The rebuilt expression
     */
    
    public String toString() {
        return builder.toString();
    }



  static protected boolean isSequenceProperty(Object property)
  {
    return FeatureMapUtil.isFeatureMap((EStructuralFeature)property);
  }



  protected void initXSD()
  {
    createDocumentRoot();
  }



    
    public synchronized Set<TopicPartition> assignment() {
        return this.subscriptions.assignedPartitions();
    }



    /**
     * Return true if the request should be validated without altering the configs.
     */
    public boolean shouldValidateOnly() {
        return validateOnly;
    }



    /**
     * Return true if the request should be validated without creating the topic.
     */
    public boolean shouldValidateOnly() {
        return validateOnly;
    }



    /**
     * Get an upper bound on the size of a batch with only a single record using a given key and value. This
     * is only an estimate because it does not take into account additional overhead from the compression
     * algorithm used.
     */
    static int estimateBatchSizeUpperBound(ByteBuffer key, ByteBuffer value, Header[] headers) {
        return RECORD_BATCH_OVERHEAD + DefaultRecord.recordSizeUpperBound(key, value, headers);
    }



    /**
     * Return true if this ResourceFilter has any UNKNOWN components.
     */
    public boolean isUnknown() {
        return resourceType.isUnknown();
    }



    /**
     * Return a map from acl filters to futures which can be used to check the status of the deletions by each
     * filter.
     */
    public Map<AclBindingFilter, KafkaFuture<FilterResults>> values() {
        return futures;
    }



    /**
     * Return a map from ACL bindings to futures which can be used to check the status of the creation of each ACL
     * binding.
     */
    public Map<AclBinding, KafkaFuture<Void>> values() {
        return futures;
    }



    public Pattern subscribedPattern() {
        return this.subscribedPattern;
    }



    /**
     * Return true if we should list internal topics.
     */
    public boolean shouldListInternal() {
        return listInternal;
    }



    /**
     * Return a map from topic names to futures which can be used to check the status of
     * individual topics.
     */
    public Map<String, KafkaFuture<TopicDescription>> values() {
        return futures;
    }



    /**
     * Return a future containing the ACLs requested.
     */
    public KafkaFuture<Collection<AclBinding>> values() {
        return future;
    }



    /**
     * Return true if this binding has any UNKNOWN components.
     */
    public boolean isUnknown() {
        return resource.isUnknown() || entry.isUnknown();
    }



    
    public Processor<K, V> get() {
        return new KStreamFilterProcessor();
    }



    /**
     * Return a map from topic names to futures, which can be used to check the status of individual
     * topic creations.
     */
    public Map<String, KafkaFuture<Void>> values() {
        return futures;
    }



    /**
     * Whether the topic is internal to Kafka. An example of an internal topic is the offsets and group management topic:
     * __consumer_offsets.
     */
    public boolean isInternal() {
        return internal;
    }



    /**
     * Return a map from resources to futures which can be used to check the status of the configuration for each
     * resource.
     */
    public Map<ConfigResource, KafkaFuture<Config>> values() {
        return futures;
    }



    /**
     * Return a map from resources to futures which can be used to check the status of the operation on each resource.
     */
    public Map<ConfigResource, KafkaFuture<Void>> values() {
        return futures;
    }



    /**
     * The number of partitions for the new topic or -1 if a replica assignment has been specified.
     */
    public int numPartitions() {
        return numPartitions;
    }



    /**
     * Get the current producer id and epoch without blocking. Callers must use {@link ProducerIdAndEpoch#isValid()} to
     * verify that the result is valid.
     *
     * @return the current ProducerIdAndEpoch.
     */
    ProducerIdAndEpoch producerIdAndEpoch() {
        return producerIdAndEpoch;
    }



    synchronized boolean isSendToPartitionAllowed(TopicPartition tp) {
        if (hasFatalError())
            return false;
        return !isTransactional() || partitionsInTransaction.contains(tp);
    }



    /**
     * Whether the topic is internal to Kafka. An example of an internal topic is the offsets and group management topic:
     * __consumer_offsets.
     */
    public boolean isInternal() {
        return internal;
    }



    /**
     * Count all in-flight requests for all nodes
     */
    public int count() {
        int total = 0;
        for (Deque<NetworkClient.InFlightRequest> deque : this.requests.values())
            total += deque.size();
        return total;
    }



    /**
     * Return the number of in-flight requests directed at the given node
     * @param node The node
     * @return The request count.
     */
    public int count(String node) {
        Deque<NetworkClient.InFlightRequest> queue = requests.get(node);
        return queue == null ? 0 : queue.size();
    }



    public static Quota upperBound(double upperBound) {
        return new Quota(upperBound, true);
    }



    public static Quota lowerBound(double lowerBound) {
        return new Quota(lowerBound, false);
    }



    public synchronized boolean hasError() {
        return currentState == State.ABORTABLE_ERROR || currentState == State.FATAL_ERROR;
    }



    /**
     * Return a map from topic names to futures which can be used to check the status of
     * individual deletions.
     */
    public Map<String, KafkaFuture<Void>> values() {
        return futures;
    }



    
    public Processor<K, V> get() {
        return new KStreamBranchProcessor();
    }



    /**
     * Get an estimate of the number of bytes written to the underlying buffer. The returned value
     * is exactly correct if the record set is not compressed or if the builder has been closed.
     */
    public int estimatedSizeInBytes() {
        return builtRecords != null ? builtRecords.sizeInBytes() : estimatedBytesWritten();
    }



    /**
     * Return a future which yields a collection of TopicListing objects.
     */
    public KafkaFuture<Collection<TopicListing>> listings() {
        return future.thenApply(new KafkaFuture.Function<Map<String, TopicListing>, Collection<TopicListing>>() {
            
            public Collection<TopicListing> apply(Map<String, TopicListing> namesToDescriptions) {
                return namesToDescriptions.values();
            }
        });
    }



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer011<>(topic, deserializationSchema, properties);
	}



	public static int getBitSize(long value){
		if(value > Integer.MAX_VALUE) {
			return 64 - Integer.numberOfLeadingZeros((int)(value >> 32));
		} else {
			return 32 - Integer.numberOfLeadingZeros((int)value);
		}
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer09<>(topic, deserializationSchema, properties);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer09<>(topic, deserializationSchema, properties);
	}



	/**
	 * Disposes the given sub slot. This method is called by the child simple slot to tell this
	 * shared slot to release it.
	 *
	 * The releasing process itself is done by the {@link SlotSharingGroupAssignment}, which controls
	 * all the modifications in this shared slot.
	 *
	 * NOTE: This method must not modify the shared slot directly !!!
	 *
	 * @param slot The sub-slot which shall be removed from the shared slot.
	 */
	void releaseChild(SimpleSlot slot) {
		assignmentGroup.releaseSimpleSlot(slot);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer09<>(topic, deserializationSchema, properties);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer011<>(topic, deserializationSchema, properties);
	}



	/**
	 * Returns the port range where the queryable state client proxy can listen.
	 * See {@link org.apache.flink.configuration.QueryableStateOptions#PROXY_PORT_RANGE QueryableStateOptions.PROXY_PORT_RANGE}.
	 */
	public Iterator<Integer> getProxyPortRange() {
		return proxyPortRange;
	}



	public static List<KafkaTopicPartition> dropLeaderData(List<KafkaTopicPartitionLeader> partitionInfos) {
		List<KafkaTopicPartition> ret = new ArrayList<>(partitionInfos.size());
		for(KafkaTopicPartitionLeader ktpl: partitionInfos) {
			ret.add(ktpl.getTopicPartition());
		}
		return ret;
	}



	void connect() throws IOException {
		if (!conn.isConnected()) {
			conn.connect();
		}
	}



	/**
	 * Returns whether this type should be automatically casted to
	 * the target type in an arithmetic operation.
	 */
	public boolean shouldAutocastTo(BasicTypeInfo<?> to) {
		for (Class<?> possibleTo: possibleCastTargetTypes) {
			if (possibleTo.equals(to.getTypeClass())) {
				return true;
			}
		}
		return false;
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer08<>(topic, deserializationSchema, properties);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer08<>(topic, deserializationSchema, properties);
	}



	
	public void onProcessingTime(long timestamp) throws Exception {
		// register next timer
		long newWatermark = userFunction.getCurrentWatermark();
		if (newWatermark > currentWatermark) {
			currentWatermark = newWatermark;
			// emit watermark
			output.emitWatermark(new Watermark(currentWatermark));
		}

		long now = getProcessingTimeService().getCurrentProcessingTime();
		getProcessingTimeService().registerTimer(now + watermarkInterval, this);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer010<>(topic, deserializationSchema, properties);
	}



	public boolean isRequiresMigration() {
		return requiresMigration;
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer011<>(topic, deserializationSchema, properties);
	}



	
	public void toggleStreamStatus(StreamStatus status) {
		if (!status.equals(this.streamStatus)) {
			this.streamStatus = status;

			// try and forward the stream status change to all outgoing connections
			for (RecordWriterOutput<?> streamOutput : streamOutputs) {
				streamOutput.emitStreamStatus(status);
			}
		}
	}



	public static Quantifier looping(final ConsumingStrategy consumingStrategy) {
		return new Quantifier(consumingStrategy, QuantifierProperty.LOOPING);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer08<>(topic, deserializationSchema, properties);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer010<>(topic, deserializationSchema, properties);
	}



	
	protected FlinkKafkaConsumerBase<Row> createKafkaConsumer(String topic, Properties properties, DeserializationSchema<Row> deserializationSchema) {
		return new FlinkKafkaConsumer010<>(topic, deserializationSchema, properties);
	}



	
	protected void doCommitInternalOffsetsToKafka(
			Map<KafkaTopicPartition, Long> offsets,
			@Nonnull KafkaCommitCallback commitCallback) throws Exception {

		ZookeeperOffsetHandler zkHandler = this.zookeeperOffsetHandler;
		if (zkHandler != null) {
			try {
				// the ZK handler takes care of incrementing the offsets by 1 before committing
				zkHandler.prepareAndCommitOffsets(offsets);
				commitCallback.onSuccess();
			}
			catch (Exception e) {
				if (running) {
					commitCallback.onException(e);
					throw e;
				} else {
					return;
				}
			}
		}

		// Set committed offsets in topic partition state
		for (KafkaTopicPartitionState<TopicAndPartition> partition : subscribedPartitionStates()) {
			Long offset = offsets.get(partition.getKafkaTopicPartition());
			if (offset != null) {
				partition.setCommittedOffset(offset);
			}
		}
	}



	public static void disableAllContextAndOtherEnvironments() {
		
		// we create a context environment that prevents the instantiation of further
		// context environments. at the same time, setting the context environment prevents manual
		// creation of local and remote environments
		ExecutionEnvironmentFactory factory = new ExecutionEnvironmentFactory() {
			
			public ExecutionEnvironment createExecutionEnvironment() {
				throw new UnsupportedOperationException("Execution Environment is already defined" +
						" for this shell.");
			}
		};
		initializeContextEnvironment(factory);
	}



	
	public void onProcessingTime(long timestamp) throws Exception {
		long currentProcessingTime = processingTimeService.getCurrentProcessingTime();

		checkForInactiveBuckets(currentProcessingTime);

		processingTimeService.registerTimer(currentProcessingTime + inactiveBucketCheckInterval, this);
	}



	
	public void complete(Collection<OUT> result) {
		resultFuture.complete(result);
	}



	
	public void completeExceptionally(Throwable error) {
		resultFuture.completeExceptionally(error);
	}



	
	public void onProcessingTime(long timestamp) throws Exception {
		// register next timer
		Watermark newWatermark = userFunction.getCurrentWatermark();
		if (newWatermark != null && newWatermark.getTimestamp() > currentWatermark) {
			currentWatermark = newWatermark.getTimestamp();
			// emit watermark
			output.emitWatermark(newWatermark);
		}

		long now = getProcessingTimeService().getCurrentProcessingTime();
		getProcessingTimeService().registerTimer(now + watermarkInterval, this);
	}



    /**
     * Save the given locale to the ActionInvocation.
     *
     * @param invocation The ActionInvocation.
     * @param locale     The locale to save.
     */
    protected void useLocale(ActionInvocation invocation, Locale locale) {
        invocation.getInvocationContext().setLocale(locale);
    }



   public static IdAndName create(String id, String name) {
      return new IdAndName(id, name);
   }



   /**
    * @see org.jclouds.googlecomputeengine.domain.Route#name()
    */
   public String name() {
      return name;
   }



   /**
    * {@inheritDoc}
    */
   
   public InputStream openStream() {
      return new ByteArrayInputStream(content);
   }



   /**
    * {@inheritDoc}
    */
   
   public InputStream openStream() {
      return content;
   }



   
   public InputStream openStream() {
      if (nodes != null)
         return Strings2.toInputStream(nodes);
      return apply(url.get());
   }



   /**
    * {@inheritDoc}
    */
   
   public InputStream openStream() {
      throw new UnsupportedOperationException();
   }



   public static Builder<?> recordIdBuilder() {
      return new ConcreteBuilder();
   }



   /**
    * {@inheritDoc}
    */
   
   public InputStream openStream() {
      return toInputStream(content);
   }



   /**
    * Make server a sandbox instance. By default, it's not.
    * 
    * @return itself for convenience
    */
   public AddServerOptions asSandboxType() {
      checkState(!queryParameters.containsKey(IS_SANDBOX_KEY),
               "Can only have one sandbox option per server");
      queryParameters.put(IS_SANDBOX_KEY, "true");
      return this;
   }



   
   public String load(RegionAndName from) {
      createPlacementGroupInRegion(from.getRegion(), from.getName());
      return from.getName();
   }



   /**
    * {@inheritDoc}
    */
   
   public InputStream openStream() {
      return new ByteArrayInputStream(bytes);
   }



   /**
    * Whether to run the script as root (or run with current privileges). By default, true.
    * 
    * @return value
    */
   public boolean shouldRunAsRoot() {
      return runAsRoot;
   }



   
   @Beta
   public Injector injector() {
      return injector;
   }



   public Long projectNumber() {
      return projectNumber;
   }



   public Versioning versioning() {
      return versioning;
   }



   public Website website() {
      return website;
   }



   public Location location() {
      return location;
   }



   public Owner owner() {
      return owner;
   }



   public Logging logging() {
      return logging;
   }



   public String name() {
      return name;
   }



   public StorageClass storageClass() {
      return storageClass;
   }



   public String contentDisposition() {
      return contentDisposition;
   }



   public String cacheControl() {
      return cacheControl;
   }



   public String contentLanguage() {
      return contentLanguage;
   }



   public String contentEncoding() {
      return contentEncoding;
   }



   public Long size() {
      return size;
   }



   public String contentType() {
      return contentType;
   }



   public Map<String, String> metadata() {
      return metadata;
   }



   public String name() {
      return name;
   }



   
   public KeyPair apply(RegionAndName from) {
      return createNewKeyPairInRegion(from.getRegion(), from.getName());
   }



   /**
    * @see org.jclouds.googlecomputeengine.domain.Firewall#network()
    */
   public URI network() {
      return network;
   }



   /**
    * @see org.jclouds.googlecomputeengine.domain.Firewall#name()
    */
   public String name() {
      return name;
   }



   protected Optional<SecurityGroupExtension> provideSecurityGroupExtension(Injector i) {
      Binding<SecurityGroupExtension> binding = i.getExistingBinding(Key.get(SecurityGroupExtension.class));
      return binding == null ? Optional.<SecurityGroupExtension> absent() : Optional.of(binding.getProvider().get());
   }



   /**
    * @param securityGroupName
    *           lists security groups by name
    */
   public ListSecurityGroupsOptions named(String securityGroupName) {
      this.queryParameters.replaceValues("securitygroupname", ImmutableSet.of(securityGroupName));
      return this;
   }



    private void emitConstructor(StringBuilder sb)
    {
        if (constructor != null)
        {
            constructor.emit(sb);
        }
    }



    public static boolean hasAnnotation(Annotation[] anns, Class<? extends Annotation> annotation)
    {
        return getAnnotation(anns, annotation) != null;
    }



    /**
     *
     * @param contextual the {@link Bean} to check
     * @return the uniqueId if it is {@link PassivationCapable} and enabled
     */
    public static String getPassivationId(Contextual<?> contextual)
    {
        if(contextual instanceof Bean)
        {
            if(contextual instanceof AbstractOwbBean)
            {
                if( ((AbstractOwbBean<?>)contextual).isPassivationCapable())
                {
                    return ((AbstractOwbBean<?>)contextual).getId();
                }
            }

            else if(contextual instanceof PassivationCapable)
            {
                PassivationCapable pc = (PassivationCapable)contextual;

                return pc.getId();
            }
        }
        else
        {
            if((contextual instanceof PassivationCapable) && (contextual instanceof Serializable))
            {
                PassivationCapable pc = (PassivationCapable)contextual;

                return pc.getId();
            }
        }

        return null;
    }



	/**
	 * Returns true if the flow execution paused and is now in a wait state.
	 * @return true if paused, false if not
	 */
	public boolean isPaused() {
		return flowExecutionKey != null;
	}



	/**
	 * Returns true if the flow execution ended.
	 * @return true if ended, false if not
	 */
	public boolean isEnded() {
		return flowExecutionKey == null;
	}



	/**
	 * Returns the mapping results recorded in this context.
	 * @return the mapping results
	 */
	public MappingResults getMappingResults() {
		return new DefaultMappingResults(source, target, mappingResults);
	}



	/**
	 * Returns the flag indicating if a flow execution redirect response has been requested by the flow.
	 */
	public boolean getFlowExecutionRedirectRequested() {
		return flowExecutionRedirectRequested;
	}



	/**
	 * Returns the flag indicating if a flow definition redirect response has been requested by the flow.
	 */
	public boolean getFlowDefinitionRedirectRequested() {
		return flowDefinitionRedirectFlowId != null;
	}



	/**
	 * Returns the flag indicating if an external redirect response has been requested by the flow.
	 */
	public boolean getExternalRedirectRequested() {
		return externalRedirectUrl != null;
	}



	/**
	 * If a redirect response has been requested, indicates if the redirect should be issued from a popup dialog.
	 */
	public boolean getRedirectInPopup() {
		return redirectInPopup;
	}



	public boolean canHandle(FlowExecutionException e) {
		return getTargetStateResolver(e) != null;
	}



	/**
	 * Returns the flag indicating if a flow execution redirect response has been requested by the flow.
	 */
	public boolean getFlowExecutionRedirectRequested() {
		return flowExecutionRedirectRequested;
	}



	/**
	 * Returns the flag indicating if an external redirect response has been requested by the flow.
	 */
	public boolean getExternalRedirectRequested() {
		return externalRedirectUrl != null;
	}



	/**
	 * If a redirect response has been requested, indicates if the redirect should be issued from a popup dialog.
	 */
	public boolean getRedirectInPopup() {
		return redirectInPopup;
	}



	/**
	 * Returns the flag indicating if a flow definition redirect response has been requested by the flow.
	 */
	public boolean getFlowDefinitionRedirectRequested() {
		return flowDefinitionRedirectFlowId != null;
	}



    /**
     * {@inheritDoc}
     *
     * The default implementation uses the identity
     * <p>{@code P(x0 < X <= x1) = P(X <= x1) - P(X <= x0)}</p>
     *
     * @since 4.0, was previously named cumulativeProbability
     */
    public double probability(int x0, int x1) throws NumberIsTooLargeException {
        if (x1 < x0) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT,
                    x0, x1, true);
        }
        return cumulativeProbability(x1) - cumulativeProbability(x0);
    }



    public boolean isEffectivelyDeferred(SQLSessionContext sc, UUID constraintId)
            throws StandardException {

        final Boolean deferred = sc.isDeferred(constraintId);
        final boolean effectivelyDeferred;
        final DataDictionary dd = getDataDictionary();

        if (deferred != null) {
            effectivelyDeferred = deferred.booleanValue();
        } else {
            // no explicit setting applicable, use initial constraint mode
            final ConstraintDescriptor conDesc =
                    dd.getConstraintDescriptor(constraintId);
            effectivelyDeferred = conDesc.initiallyDeferred();
        }

        return effectivelyDeferred;
    }



    synchronized StorageRandomAccessFile getRandomAccessFile(StorageFile file)
        throws SecurityException, StandardException
    {
        actionCode = GET_RANDOM_ACCESS_FILE_ACTION;
        actionFile = file;
        try
        {
            return (StorageRandomAccessFile)AccessController.doPrivileged(this);
        }
        catch( PrivilegedActionException pae){ 
            throw (StandardException) pae.getException();
        }
        finally{ actionFile = null; }
    }



    /**
     * Add a SQLWarning to this Statement object.
     * If the Statement already has a SQLWarning then it
     * is added to the end of the chain.
     * 
     * @see #getWarnings()
     */
	final void addWarning(SQLWarning sw)
	{
		if (sw != null) {
			if (warnings == null)
				warnings = sw;
			else
				warnings.setNextException(sw);
		}
	}



    /**
     * {@inheritDoc}
     */
    public void pushNestedSessionContext(
        Activation a,
        boolean definersRights,
        String definer) throws StandardException {

        setupSessionContextMinion(a, true, definersRights, definer);
    }



	/**
     * Unblock the backup, a backup blocking operation finished. 
	 */
	protected void unblockBackup()
	{
		synchronized(backupSemaphore) {
			if (SanityManager.DEBUG)
				SanityManager.ASSERT(backupBlockingOperations > 0, 
                    "no backup blocking opeations in progress"); 
			
			backupBlockingOperations--;

			if (inBackup) {
				// wake up the online backupthread
				backupSemaphore.notifyAll(); 
			}
		}
	}



	/**
	 * Backup completed. Allow backup blocking operations. 
	 */
	public void unblockBackupBlockingOperations()
	{
		synchronized(backupSemaphore) {
			inBackup = false;
			backupSemaphore.notifyAll();
		}
	}



	/**
	 * @see ResultSetNode#adjustForSortElimination
	 */
	void adjustForSortElimination()
	{
		/* NOTE: We use a different method to tell a FBT that
		 * it cannot do a bulk fetch as the ordering issues are
		 * specific to a FBT being under an IRTBR as opposed to a
		 * FBT being under a PRN, etc.
		 */
		source.disableBulkFetch();
	}



	/**
	 * Set the DataValueDescriptor and type information for this parameter
	 *
	 */
	void initialize(DataValueDescriptor value, int jdbcTypeId, String className)
	{
		this.value = value;
		this.jdbcTypeId = jdbcTypeId;
		this.declaredClassName = className;
	}



    public void startNewLogFile() throws StandardException
    {
        // switch the database to a new log file.
        switchLogFile();
    }



	/**
	 * Find a dependable object, which is essentially a table descriptor with
	 * referencedColumnMap field set.
	 *
	 * @param	dd data dictionary
	 * @param	dependableObjectID dependable object ID (table UUID)
	 * @return	a dependable, a table descriptor with referencedColumnMap
	 *			field set
	 */
	Dependable findDependable(DataDictionary dd, UUID dependableObjectID)
		throws StandardException
	{
		TableDescriptor td = dd.getTableDescriptor(dependableObjectID);
		if (td != null)  // see beetle 4444
			td.setReferencedColumnMap(new FormatableBitSet(columnBitMap));
		return td;
	}



    public String loadReference() {
        try {
            return DOMUtil.asIndentedXML(endpoint.getAsReference(null));
        } catch (TransformerException e) {
            return null;
        }
    }



    public String loadWSDL() {
        try {
            return DOMUtil.asXML(registry.getEndpointDescriptor(endpoint));
        } catch (Exception e) {
            return null;
        }
    }



    public Param createParam() {
        Param p = new Param();
        if (nestedParams == null) {
            nestedParams = new ArrayList();
        }
        nestedParams.add(p);
        return p;
    }



    /**
     * <b>DOM</b>: Returns whether <code>preventDefault</code> has been
     * called on this object.
     */
    public boolean getDefaultPrevented() {
        return preventDefault;
    }



  private MetaResultSet clientGetCatalogs() {
    StringBuilder sb = new StringBuilder();
    sb.append("select "
        + "CATALOG_NAME as TABLE_CAT "
        + " FROM INFORMATION_SCHEMA.CATALOGS ");

    sb.append(" ORDER BY CATALOG_NAME");

    return s(sb.toString());
  }



  private MetaResultSet clientGetSchemas(String catalog, Pat schemaPattern) {
    StringBuilder sb = new StringBuilder();
    sb.append("select "
        + "SCHEMA_NAME as TABLE_SCHEM, "
        + "CATALOG_NAME as TABLE_CAT "
        + " FROM INFORMATION_SCHEMA.SCHEMATA WHERE 1=1 ");

    if (catalog != null) {
      sb.append(" AND CATALOG_NAME = '" + DrillStringUtils.escapeSql(catalog) + "' ");
    }
    if (schemaPattern.s != null) {
      sb.append(" AND SCHEMA_NAME like '" + DrillStringUtils.escapeSql(schemaPattern.s) + "'");
    }
    sb.append(" ORDER BY CATALOG_NAME, SCHEMA_NAME");

    return s(sb.toString());
  }



  
  public void close() throws IOException {
    currentContainer.zeroVectors();
    if (sv2 != null) {
      sv2.clear();
    }
    if (outputStream != null) {
      outputStream.close();
    }
    if (inputStream != null) {
      inputStream.close();
    }
    if (fs != null && fs.exists(path)) {
      fs.delete(path, false);
    }
  }



  
  Iterable<OptionValue> getLocalOptions() {
    final Collection<OptionValue> liveOptions = Collections2.filter(options.values(), isLive);
    return liveOptions;
  }



  private JsonResult message(String message) {
    return new JsonResult(message);
  }



  
  public void close() {
    try {
      if (pageReadStore != null) {
        pageReadStore.close();
        pageReadStore = null;
      }
    } catch (IOException e) {
      logger.warn("Failure while closing PageReadStore", e);
    }
  }



  public void unpauseFragment(final RpcOutcomeListener<Ack> outcomeListener, final FragmentHandle handle) {
    final SignalFragment b = new SignalFragment(outcomeListener, handle, RpcType.REQ_UNPAUSE_FRAGMENT);
    manager.runCommand(b);
  }



  
  public void clear() {
    if(!releasable) return;
    for(T x : vectors){
      x.clear();  
    }
    
  }



  /**
   * Creates a {{@link DrillConfig configuration}} disabling server specific configuration options.
   *
   * @return {{@link DrillConfig}} instance
   */
  public static DrillConfig forClient() {
    return create(null, false);
  }



  public String build() {
    String rv;
    rv = sb.append("\n</table>").toString();
    sb = null;
    return rv;
  }



  protected ServiceInstance<DrillbitEndpoint> newServiceInstance(DrillbitEndpoint endpoint) throws Exception {
    return ServiceInstance.<DrillbitEndpoint>builder()
      .name(serviceName)
      .payload(endpoint)
      .build();
  }



  protected ServiceDiscovery<DrillbitEndpoint> newDiscovery() {
    return ServiceDiscoveryBuilder
      .builder(DrillbitEndpoint.class)
      .basePath("/")
      .client(curator)
      .serializer(DrillServiceInstanceHelper.SERIALIZER)
      .build();
  }



  
  Iterable<OptionValue> getLocalOptions() {
    return options.values();
  }



  /**
   * Throws AlreadyClosedSqlException if the associated Connection is closed.
   *
   * @throws AlreadyClosedSqlException if Connection is closed
   * @throws SQLException if error in calling {@link Connection#isClosed()}
   */
  private void throwIfClosed() throws AlreadyClosedSqlException,
                                      SQLException {
    if ( getConnection().isClosed() ) {
      throw new AlreadyClosedSqlException(
          "DatabaseMetaData's Connection is already closed." );
    }
  }



  /**
   * Throws AlreadyClosedSqlException <i>iff</i> this Statement is closed.
   *
   * @throws  AlreadyClosedSqlException  if Statement is closed
   */
  private void throwIfClosed() throws AlreadyClosedSqlException {
    if ( isClosed() ) {
      throw new AlreadyClosedSqlException( "Statement is already closed." );
    }
  }



  public int avgDensity() { return avgDensity; }



  public boolean isServerMetadataDisabled() {
    return Boolean.valueOf(props.getProperty("server.metadata.disabled"));
  }



  public boolean isServerPreparedStatementDisabled() {
    return Boolean.valueOf(props.getProperty("server.preparedstatement.disabled"));
  }



  private void reset() {
    resetValues();
    for (VectorWrapper<?> vw : internal) {
      if ((vw.getValueVector() instanceof BaseDataValueVector)) {
        ((BaseDataValueVector) vw.getValueVector()).reset();
      }
    }
  }



  
  public void close() {
    try {
      if (reader != null) {
        reader.close();
        reader = null;
      }
    } catch (IOException e) {
      logger.warn("Exception closing reader: {}", e);
    }
  }



  
  public boolean satisfies(RelTrait trait) {

    if (trait instanceof DrillDistributionTrait) {
      DistributionType requiredDist = ((DrillDistributionTrait) trait).getType();
      if (requiredDist == DistributionType.ANY) {
        return true;
      }

      if (this.type == DistributionType.HASH_DISTRIBUTED) {
        if (requiredDist == DistributionType.HASH_DISTRIBUTED) {
          // A subset of the required distribution columns can satisfy (subsume) the requirement
          // e.g: required distribution: {a, b, c}
          // Following can satisfy the requirements: {a}, {b}, {c}, {a, b}, {b, c}, {a, c} or {a, b, c}

          // New: Use equals for subsumes check of hash distribution. If we uses subsumes,
          // a join may end up with hash-distributions using different keys. This would
          // cause incorrect query result.
          return this.equals(trait);
        }
        else if (requiredDist == DistributionType.RANDOM_DISTRIBUTED) {
          return true; // hash distribution subsumes random distribution and ANY distribution
        }
      }
    }

    return this.equals(trait);
  }



  
  public void close() {
    if (reader != null) {
      try {
        reader.close();
      } catch (IOException e) {
        logger.warn("Error closing Avro reader", e);
      } finally {
        reader = null;
      }
    }
  }



  /**
   * Throws AlreadyClosedSqlException <i>iff</i> this Connection is closed.
   *
   * @throws  AlreadyClosedSqlException  if Connection is closed
   */
  private void throwIfClosed() throws AlreadyClosedSqlException {
    if ( isClosed() ) {
      throw new AlreadyClosedSqlException( "Connection is already closed." );
    }
  }



  
  public void clear() {
    v.clear();
  }



    /**
     * Applies the closure to each element of the provided iterator.
     *
     * @param <E>  the element type
     * @param iterator  the iterator to use, may be null
     * @param closure  the closure to apply to each element, may not be null
     * @throws NullPointerException if closure is null
     * @since 4.1
     */
    public static <E> void forEach(final Iterator<E> iterator, final Closure<? super E> closure) {
        if (closure == null) {
            throw new NullPointerException("Closure must not be null");
        }

        if (iterator != null) {
            while (iterator.hasNext()) {
                final E element = iterator.next();
                closure.execute(element);
            }
        }
    }



    /**
     * Returns a new hash set that matches elements based on <code>==</code> not
     * <code>equals()</code>.
     * <p>
     * <strong>This set will violate the detail of various Set contracts.</note>
     * As a general rule, don't compare this set to other sets. In particular, you can't
     * use decorators like {@link ListOrderedSet} on it, which silently assume that these
     * contracts are fulfilled.</strong>
     * <p>
     * <strong>Note that the returned set is not synchronized and is not thread-safe.</strong>
     * If you wish to use this set from multiple threads concurrently, you must use
     * appropriate synchronization. The simplest approach is to wrap this map
     * using {@link java.util.Collections#synchronizedSet(Set)}. This class may throw
     * exceptions when accessed by concurrent threads without synchronization.
     *
     * @param <E>  the element type
     * @return a new identity hash set
     * @since 4.1
     */
    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap<E, Boolean>());
    }



    public void notifyListeners(JobBaseAPIEntity entity) throws Exception {
        for (HistoryJobEntityCreationListener l : listeners) {
            l.jobEntityCreated(entity);
        }
    }



    
    public void destroy() {
        if (getWebView() != null) {
            getWebView().removeAllViews();
            getWebView().destroy();
            mWebView = null;
        }
    }



  /**
   * For now, this method respect the result of {@link WXSDKInstance#isLayerTypeEnabled()}
   * @return Refer {@link WXSDKInstance#isLayerTypeEnabled()}
   */
  public boolean isLayerTypeEnabled() {
    return getInstance().isLayerTypeEnabled();
  }



  public void setContext(@NonNull Context context) {
    this.mContext = context;
  }



   public void asetMimeTypes(List<String> mimeTypes) {
      this.mimeTypes = mimeTypes;
   }



  public static String lineListToString(final List<Line> list) {
    StringBuilder builder = new StringBuilder();

    for (Line currentLine : list) {
      builder.append(currentLine.toString());
    }

    return builder.toString();
  }



  public EdmMultiplicity extractMultiplicity(final EdmNavigationProperty enp, final Field field) {
    EdmMultiplicity multiplicity = mapMultiplicity(enp.toMultiplicity());
    final boolean isCollectionType = field.getType().isArray() || Collection.class.isAssignableFrom(field.getType());

    if (multiplicity == EdmMultiplicity.ONE && isCollectionType) {
      return EdmMultiplicity.MANY;
    }
    return multiplicity;
  }



  /** Returns a copy of this list with one element added. */
  public ImmutableIntList append(int element) {
    if (ints.length == 0) {
      return of(element);
    }
    final int[] newInts = Arrays.copyOf(this.ints, ints.length + 1);
    newInts[ints.length] = element;
    return new ImmutableIntList(newInts);
  }



  public boolean satisfies(RelTrait trait) {
    return this == trait
        || trait instanceof RelCollationImpl
        && Util.startsWith(fieldCollations,
            ((RelCollationImpl) trait).fieldCollations);
  }



  private Multimap<String, Function> createFunctionMap() {
    final ImmutableMultimap.Builder<String, Function> builder =
        ImmutableMultimap.builder();
    for (Method method : clazz.getMethods()) {
      final String methodName = method.getName();
      if (method.getDeclaringClass() == Object.class
          || methodName.equals("toString")) {
        continue;
      }
      if (TranslatableTable.class.isAssignableFrom(method.getReturnType())) {
        final TableMacro tableMacro =
            new MethodTableMacro(this, method);
        builder.put(methodName, tableMacro);
      }
    }
    return builder.build();
  }



    /**
     * Gets the value of the flag that determines if JMX will be enabled for
     * pools created with this configuration instance.
     */
    public boolean getJmxEnabled() {
        return jmxEnabled;
    }



    
    public Repository connectRepository(RepositoryInfo repositoryInfo) throws RepositoryException {
        //TODO: currently not doing repository-caching
        return new RepositoryImpl(repositoryInfo, eventAdmin);
    }



    public void disable() {
        for (String jobName : registeredJobs) {
            boolean result = scheduler.unschedule(jobName);
            log.info("handler unregistered {} {}", jobName, result);

        }
    }



	
	public boolean test(Resource resource) {
		Resource property = resource.getChild(key);
		if (property == null) {
			return false;
		} else if (value == null) {
			return true;
		} else {
			return isEqualToValue(property);
		}
	}



    static InputStream toInputStream(String source) {
        byte[] data = fromIdentityEncodedString(source);
        return new ByteArrayInputStream(data);
    }



    /**
     * Make this object read-only. All method calls trying to modify this object
     * result in an exception!
     */
    public void lock() {
        this.isReadOnly = true;
    }



    static void info(String message, Throwable t) {
        log(System.out, "*INFO*", message, t);
    }



	
	public boolean test(T element) {
		for (T t : iterable) {
			if (provider.sameElement(t, element)) {
				return true;
			}
		}
		return false;
	}



	/**
	 * XSS encodes the specified text using the specified mode.
	 * 
	 * @param value
	 *            The text to encode
	 * @param mode
	 *            The XSS mode to use, see XSSSupport for the list of available modes
	 * @return the encoded text
	 */
	public static String encode(String value, String mode) {
		return XSSSupport.encode(value, XSSSupport.getEncodingMode(mode));
	}



    private String toString(RuntimeObjectModel runtimeObjectModel, Object[] params, int index) {
        if (index >= 0 && index < params.length) {
            return runtimeObjectModel.toString(params[index]);
        }
        return "";
    }



    /**
     * Reset the thread context resource resolver.
     */
    void resetRequestResourceResolver(final ResourceResolver resolver) {
        requestResourceResolver.set(resolver);
    }



    public boolean isModifiable() {
        return this.modifiable;
    }



	
	public boolean test(T resource) {
		return provider.listChildren(resource).hasNext();
	}



    /**
     * Return a new session.
     */
    public Session createSession() throws RepositoryException {
        // get an administrative session for potentiall impersonation
        final Session admin = this.repository.loginAdministrative(null);

        // do use the admin session, if the admin's user id is the same as owner
        if (admin.getUserID().equals(this.classLoaderOwner)) {
            return admin;
        }

        // else impersonate as the owner and logout the admin session again
        try {
            return admin.impersonate(new SimpleCredentials(this.classLoaderOwner, new char[0]));
        } finally {
            admin.logout();
        }
    }



    private void clearDisposalCallbackRegistryQueue() {
        java.lang.ref.Reference<?> ref = queue.poll();
        while (ref != null) {
            log.debug("calling disposal for {}.", ref.toString());
            DisposalCallbackRegistryImpl registry = disposalCallbacks.remove(ref);
            registry.onDisposed();
            ref = queue.poll();
        }
    }



    /**
     * Find the feature if available
     * @param name The feature name
     * @return The feature or {@code null}.
     */
    public Feature getFeature(final String name) {
        for(final Feature f : this.features) {
            if ( name.equals(f.getName()) ) {
                return f;
            }
        }
        return null;
    }



    /**
     * Return the cached result if it's still valid.
     */
    public HealthCheckExecutionResult getValidCacheResult(final HealthCheckMetadata metadata,
            final long resultCacheTtlInMs) {
        return get(metadata, resultCacheTtlInMs);
    }



    
    public Set<String> getPropertyNames() {
        Object[] properties = scriptable.getIds();
        Set<String> keys = new HashSet<String>();
        for (Object property: properties) {
            if (property instanceof String) {
                keys.add((String) property);
            }
        }
        return keys;
    }



    /**
     * With a call to this method, the provider implementation is notified that
     * it is used in the resource tree.
     * @param ctx The context for this provider.
     */
    public void start(@Nonnull ProviderContext ctx) {
        this.ctx = ctx;
    }



    /**
     * With a call to this method, the provider implementation is notified
     * that it is not used anymore in the resource tree.
     */
    public void stop() {
        this.ctx = null;
    }



    /**
     * Maybe called by the application to cause the Sling Application to
     * properly terminate by stopping the OSGi Framework.
     * <p>
     * After calling this method the Sling Application can be started again
     * by calling the {@link #doStart()} method.
     * <p>
     * Calling this method multiple times without calling the {@link #doStart()}
     * method in between has no effect after the Sling Application has been
     * terminated.
     */
    protected void doStop() {
        removeShutdownHook();

        // now really shutdown sling
        if (this.sling != null) {
            info("Stopping Apache Sling", null);
            this.sling.stop();
            this.sling = null;
        }

        // clean and VM caches
        if (this.loader != null) {
            this.loader.cleanupVM();
            this.loader = null;
        }

        // further cleanup
        this.started = false;
    }



    
    public PipeBuilder newPipe(ResourceResolver resolver) {
        PipeBuilder builder = new PipeBuilderImpl(resolver, this);
        return builder;
    }



	
	public boolean test(T resource) {
		Iterator<Option<T>> result = apply(IteratorUtils.singleElementIterator(Option.of(resource, 0)));
		return new EmptyElementFilter<T>(result).hasNext();
	}



    
    public PipeBuilder grep(Object... conf) throws IllegalAccessException {
        return pipe(FilterPipe.RESOURCE_TYPE).conf(conf);
    }



    
    protected FileVaultMetaInfRootFolder findRootFolder(IProject project) {
        if (ProjectHelper.isContentProject(project)) {
            IFolder syncDir = ProjectUtil.getSyncDirectory(project);
            if (syncDir != null) {
                IFolder metaInfFolder = syncDir.getParent().getFolder(new Path(FILEVAULT_METAINF_PATH));
                if (metaInfFolder.exists()) {
                    return new FileVaultMetaInfRootFolder(metaInfFolder);
                }
            }
        }
        return null;    	
    }



    public boolean isConfirmed()
    {
        return confirmed;
    }



    public String input()
        throws ContinuumException
    {
        try
        {
            checkAddProjectNotifierAuthorization( getProjectGroupName() );
        }
        catch ( AuthorizationRequiredException authzE )
        {
            addActionError( authzE.getMessage() );
            return REQUIRES_AUTHORIZATION;
        }

        return INPUT;
    }



    public String input()
        throws ContinuumException
    {
        try
        {
            checkAddProjectGroupNotifierAuthorization( getProjectGroupName() );
        }
        catch ( AuthorizationRequiredException authzE )
        {
            addActionError( authzE.getMessage() );
            return REQUIRES_AUTHORIZATION;
        }

        return INPUT;
    }



    public String input()
    {
        try
        {
            checkAddProjectGroupAuthorization();

            return INPUT;
        }
        catch ( AuthorizationRequiredException authzE )
        {
            addActionError( authzE.getMessage() );
            return REQUIRES_AUTHORIZATION;
        }
    }



    public String input()
        throws Exception
    {
        return REQUIRES_AUTHORIZATION;
    }



  private void refreshSelection() {
    mFeatureStructureSelectionProvider.setSelection(getDocument(), getSelectedAnnotations());
  }



  
  public IntListIterator iterator() {
    return new IntHashSetIterator();
  }



  public CASImpl getCASImpl() { // was package private 9-03
    return this._casView;
  }



  
  public IntBitSetIterator iterator() {
    return new IntBitSetIterator();
  }



	/**
	 * Obtain the frame as a SystemML FrameObject.
	 * 
	 * @return the frame as a SystemML FrameObject
	 */
	public FrameObject toFrameObject() {
		return frameObject;
	}



	/**
	 * Obtain the matrix as a SystemML MatrixObject.
	 * 
	 * @return the matrix as a SystemML MatrixObject
	 */
	public MatrixObject toMatrixObject() {
		return matrixObject;
	}



	private int internPosFIndexGTE(int r, int c) {
		int pos = pos(r);
		int len = size(r);
		
		//search for existing col index
		int index = Arrays.binarySearch(_indexes, pos, pos+len, c);
		if( index >= 0  )
			return (index < pos+len) ? index : -1;
		
		//search gt col index (see binary search)
		index = Math.abs( index+1 );
		return (index < pos+len) ? index : -1;
	}



	public boolean isColLowerEqualsUpper() {
		return _colLowerEqualsUpper;
	}



	public boolean isRowLowerEqualsUpper(){
		return _rowLowerEqualsUpper;
	}



	private int internPosFIndexGTE(int r, int c) {
		int pos = pos(r);
		int len = size(r);
		
		//search for existing col index
		int index = Arrays.binarySearch(_cindexes, pos, pos+len, c);
		if( index >= 0  )
			return (index < pos+len) ? index : -1;
		
		//search gt col index (see binary search)
		index = Math.abs( index+1 );
		return (index < pos+len) ? index : -1;
	}



	public boolean isRowLowerEqualsUpper(){
		return _rowLowerEqualsUpper;
	}



	public boolean isColLowerEqualsUpper() {
		return _colLowerEqualsUpper;
	}



  /**
   * Same as reportDiagnosticInfo but does not authorize caller. This is used
   * internally within MapReduce, whereas reportDiagonsticInfo may be called
   * via RPC.
   */
  synchronized void internalReportDiagnosticInfo(TaskAttemptID taskid, String info) throws IOException {
    TaskInProgress tip = tasks.get(taskid);
    if (tip != null) {
      tip.reportDiagnosticInfo(info);
    } else {
      LOG.warn("Error from unknown child task: "+taskid+". Ignored.");
    }
  }



  /**
   * Version of fsError() that does not do authorization checks, called by
   * the TaskRunner.
   */
  synchronized void internalFsError(TaskAttemptID taskId, String message)
  throws IOException {
    LOG.fatal("Task: " + taskId + " - Killed due to FSError: " + message);
    TaskInProgress tip = runningTasks.get(taskId);
    tip.reportDiagnosticInfo("FSError: " + message);
    purgeTask(tip, true);
  }



  /**
   * Cleanup when the {@link TaskTracker} is declared as 'lost/blacklisted'
   * by the JobTracker.
   * 
   * The method assumes that the lock on the {@link JobTracker} is obtained
   * by the caller.
   */
  public void cancelAllReservations() {
    // Inform jobs which have reserved slots on this tasktracker
    if (jobForFallowMapSlot != null) {
      unreserveSlots(TaskType.MAP, jobForFallowMapSlot);
    }
    if (jobForFallowReduceSlot != null) {
      unreserveSlots(TaskType.REDUCE, jobForFallowReduceSlot);
    }
  }



    public Entity copy() {
        return fromString(getEntityType(), toString());
    }



    public static Properties getClusterProperties(org.apache.falcon.entity.v0.cluster.Cluster cluster) {
        Properties properties = new Properties();
        Map<String, String> clusterVars = new HashMap<String, String>();
        clusterVars.put("colo", cluster.getColo());
        clusterVars.put("name", cluster.getName());
        if (cluster.getProperties() != null) {
            for (Property property : cluster.getProperties().getProperties()) {
                clusterVars.put(property.getName(), property.getValue());
            }
        }
        properties.put("cluster", clusterVars);
        return properties;
    }



    private boolean isFilteredByTags(List<String> filterTagsList, List<String> tags) {
        if (filterTagsList.isEmpty()) {
            return false;
        } else if (tags.isEmpty()) {
            return true;
        }

        for (String tag : filterTagsList) {
            if (!tags.contains(tag)) {
                return true;
            }
        }

        return false;
    }



    public static List<ClusterMerlin> getClustersFromStrings(List<String> clusterStrings) {
        List<ClusterMerlin> clusters = new ArrayList<ClusterMerlin>();
        for (String clusterString : clusterStrings) {
            clusters.add(new ClusterMerlin(clusterString));
        }
        return clusters;
    }



    /**
     * When {@link #getMaxConnLifetimeMillis()} is set to limit connection lifetime,
     * this property determines whether or not log messages are generated when the
     * pool closes connections due to maximum lifetime exceeded.
     *
     * @since 2.1
     */
    
    public boolean getLogExpiredConnections() {
        return logExpiredConnections;
    }



    /**
     * True means that validation will fail immediately for connections that
     * have previously thrown SQLExceptions with SQL_STATE indicating fatal
     * disconnection errors.
     *
     * @return true if connections created by this datasource will fast fail validation.
     * @see #setDisconnectionSqlCodes(Collection)
     * @since 2.1
     */
    
    public boolean getFastFailValidation() {
        return fastFailValidation;
    }



  public TableDescriptor build() {
    return new ModifyableTableDescriptor(desc);
  }



  /**
   * Converts a ColumnFamilySchema to ColumnFamilyDescriptor
   * @param cfs the ColumnFamilySchema
   * @return An {@link ColumnFamilyDescriptor} made from the passed in <code>cfs</code>
   */
  public static ColumnFamilyDescriptor toColumnFamilyDescriptor(final ColumnFamilySchema cfs) {
    // Use the empty constructor so we preserve the initial values set on construction for things
    // like maxVersion.  Otherwise, we pick up wrong values on deserialization which makes for
    // unrelated-looking test failures that are hard to trace back to here.
    ColumnFamilyDescriptorBuilder builder
      = ColumnFamilyDescriptorBuilder.newBuilder(cfs.getName().toByteArray());
    cfs.getAttributesList().forEach(a -> builder.setValue(a.getFirst().toByteArray(), a.getSecond().toByteArray()));
    cfs.getConfigurationList().forEach(a -> builder.setConfiguration(a.getName(), a.getValue()));
    return builder.build();
  }



  /**
   * Display the full report from fsck. This displays all live and dead region
   * servers, and all known regions.
   */
  public void setDisplayFullReport() {
    details = true;
  }



  public long heapSize() {
    return getCurrentSize();
  }



  /**
   * Pass along the procedure global barrier notification to any listeners
   * @param path full znode path that cause the notification
   */
  private void receivedReachedGlobalBarrier(String path) {
    LOG.debug("Recieved reached global barrier:" + path);
    String procName = ZKUtil.getNodeName(path);
    this.member.receivedReachedGlobalBarrier(procName);
  }



  /**
   * Indicates whether the current mob ref cell has a valid value.
   * A mob ref cell has a mob reference tag.
   * The value of a mob ref cell consists of two parts, real mob value length and mob file name.
   * The real mob value length takes 4 bytes.
   * The remaining part is the mob file name.
   * @param cell The mob ref cell.
   * @return True if the cell has a valid value.
   */
  public static boolean hasValidMobRefCellValue(Cell cell) {
    return cell.getValueLength() > Bytes.SIZEOF_INT;
  }



  /**
   * @return Content of the clusterup znode as a serialized pb with the pb
   * magic as prefix.
   */
  static byte [] toByteArray() {
    ZooKeeperProtos.ClusterUp.Builder builder =
      ZooKeeperProtos.ClusterUp.newBuilder();
    builder.setStartDate(new java.util.Date().toString());
    return ProtobufUtil.prependPBMagic(builder.build().toByteArray());
  }



  /**
   * @return true when we may have more cells for the current row. This usually because we have
   *         reached a limit in the middle of a row
   */
  boolean mayHaveMoreCellsInRow() {
    return scannerState == NextState.SIZE_LIMIT_REACHED_MID_ROW ||
        scannerState == NextState.TIME_LIMIT_REACHED_MID_ROW ||
        scannerState == NextState.BATCH_LIMIT_REACHED;
  }



  /**
   * Make changes visible.
   * Caller must be synchronized on 'this'.
   */
  private void resetRSGroupAndTableMaps(Map<String, RSGroupInfo> newRSGroupMap,
      Map<TableName, String> newTableMap) {
    // Make maps Immutable.
    this.rsGroupMap = Collections.unmodifiableMap(newRSGroupMap);
    this.tableMap = Collections.unmodifiableMap(newTableMap);
  }



  private static void runZKServer(QuorumPeerConfig zkConfig) throws UnknownHostException, IOException {
    if (zkConfig.isDistributed()) {
      QuorumPeerMain qp = new QuorumPeerMain();
      qp.runFromConfig(zkConfig);
    } else {
      ZooKeeperServerMain zk = new ZooKeeperServerMain();
      ServerConfig serverConfig = new ServerConfig();
      serverConfig.readFrom(zkConfig);
      zk.runFromConfig(serverConfig);
    }
  }



  
  public CompletableFuture<Boolean> catalogJanitorSwitch(boolean enabled) {
    return this
        .<Boolean> newMasterCaller()
        .action(
          (controller, stub) -> this
              .<EnableCatalogJanitorRequest, EnableCatalogJanitorResponse, Boolean> call(
                controller, stub, RequestConverter.buildEnableCatalogJanitorRequest(enabled), (s,
                    c, req, done) -> s.enableCatalogJanitor(c, req, done), (resp) -> resp
                    .getPrevValue())).call();
  }



  
  public CompletableFuture<Boolean> splitSwitch(boolean on) {
    return setSplitOrMergeOn(on, MasterSwitchType.SPLIT);
  }



  
  public CompletableFuture<Boolean> cleanerChoreSwitch(boolean enabled) {
    return this
        .<Boolean> newMasterCaller()
        .action(
          (controller, stub) -> this
              .<SetCleanerChoreRunningRequest, SetCleanerChoreRunningResponse, Boolean> call(
                controller, stub, RequestConverter.buildSetCleanerChoreRunningRequest(enabled), (s,
                    c, req, done) -> s.setCleanerChoreRunning(c, req, done), (resp) -> resp
                    .getPrevValue())).call();
  }



  
  public CompletableFuture<Boolean> mergeSwitch(boolean on) {
    return setSplitOrMergeOn(on, MasterSwitchType.MERGE);
  }



  
  public CompletableFuture<Boolean> normalizerSwitch(boolean on) {
    return this
        .<Boolean> newMasterCaller()
        .action(
          (controller, stub) -> this
              .<SetNormalizerRunningRequest, SetNormalizerRunningResponse, Boolean> call(
                controller, stub, RequestConverter.buildSetNormalizerRunningRequest(on), (s, c,
                    req, done) -> s.setNormalizerRunning(c, req, done), (resp) -> resp
                    .getPrevNormalizerValue())).call();
  }



  
  public CompletableFuture<Boolean> balancerSwitch(final boolean on) {
    return this
        .<Boolean> newMasterCaller()
        .action(
          (controller, stub) -> this
              .<SetBalancerRunningRequest, SetBalancerRunningResponse, Boolean> call(controller,
                stub, RequestConverter.buildSetBalancerRunningRequest(on, true),
                (s, c, req, done) -> s.setBalancerRunning(c, req, done),
                (resp) -> resp.getPrevBalanceValue())).call();
  }



  public boolean isUseHBaseChecksum() {
    return usesHBaseChecksum;
  }



  public boolean isCompressTags() {
    return compressTags;
  }



  public boolean isIncludesMvcc() {
    return includesMvcc;
  }



  public boolean isIncludesTags() {
    return includesTags;
  }



  /** An additional sanity-check in case no compression or encryption is being used. */
  public void sanityCheckUncompressedSize() throws IOException {
    if (onDiskSizeWithoutHeader != uncompressedSizeWithoutHeader + totalChecksumBytes()) {
      throw new IOException("Using no compression but "
          + "onDiskSizeWithoutHeader=" + onDiskSizeWithoutHeader + ", "
          + "uncompressedSizeWithoutHeader=" + uncompressedSizeWithoutHeader
          + ", numChecksumbytes=" + totalChecksumBytes());
    }
  }



  
  public boolean shouldPerformMajorCompaction() throws IOException {
    for (HStoreFile sf : this.storeEngine.getStoreFileManager().getStorefiles()) {
      // TODO: what are these reader checks all over the place?
      if (sf.getReader() == null) {
        LOG.debug("StoreFile " + sf + " has null Reader");
        return false;
      }
    }
    return storeEngine.getCompactionPolicy().shouldPerformMajorCompaction(
        this.storeEngine.getStoreFileManager().getStorefiles());
  }



  
  public void snapshotAsync(SnapshotDescription snapshotDesc) throws IOException,
      SnapshotCreationException {
    asyncSnapshot(ProtobufUtil.createHBaseProtosSnapshotDesc(snapshotDesc));
  }



  
  public TableDescriptor getDescriptor(TableName tableName) throws TableNotFoundException, IOException {
    return getTableDescriptor(tableName, getConnection(), rpcCallerFactory, rpcControllerFactory,
       operationTimeout, rpcTimeout);
  }



  
  public boolean cleanerChoreSwitch(final boolean on) throws IOException {
    return executeCallable(new MasterCallable<Boolean>(getConnection(), getRpcControllerFactory()) {
       public Boolean rpcCall() throws Exception {
        return master.setCleanerChoreRunning(getRpcController(), RequestConverter
                                                                   .buildSetCleanerChoreRunningRequest(
                                                                     on)).getPrevValue();
      }
    });
  }



  
  public boolean catalogJanitorSwitch(final boolean enable) throws IOException {
    return executeCallable(new MasterCallable<Boolean>(getConnection(), getRpcControllerFactory()) {
      
      protected Boolean rpcCall() throws Exception {
        return master.enableCatalogJanitor(getRpcController(),
          RequestConverter.buildEnableCatalogJanitorRequest(enable)).getPrevValue();
      }
    });
  }



  
  public boolean balancerSwitch(final boolean on, final boolean synchronous)
  throws IOException {
    return executeCallable(new MasterCallable<Boolean>(getConnection(), getRpcControllerFactory()) {
      
      protected Boolean rpcCall() throws Exception {
        SetBalancerRunningRequest req =
            RequestConverter.buildSetBalancerRunningRequest(on, synchronous);
        return master.setBalancerRunning(getRpcController(), req).getPrevBalanceValue();
      }
    });
  }



  
  public boolean normalizerSwitch(final boolean on) throws IOException {
    return executeCallable(new MasterCallable<Boolean>(getConnection(), getRpcControllerFactory()) {
      
      protected Boolean rpcCall() throws Exception {
        SetNormalizerRunningRequest req =
          RequestConverter.buildSetNormalizerRunningRequest(on);
        return master.setNormalizerRunning(getRpcController(), req).getPrevNormalizerValue();
      }
    });
  }



  public static byte[] cloneQualifier(Cell cell){
    byte[] output = new byte[cell.getQualifierLength()];
    copyQualifierTo(cell, output, 0);
    return output;
  }



  public static byte[] cloneFamily(Cell cell){
    byte[] output = new byte[cell.getFamilyLength()];
    copyFamilyTo(cell, output, 0);
    return output;
  }



  /***************** get individual arrays for tests ************/

  public static byte[] cloneRow(Cell cell){
    byte[] output = new byte[cell.getRowLength()];
    copyRowTo(cell, output, 0);
    return output;
  }



  public static byte[] cloneValue(Cell cell){
    byte[] output = new byte[cell.getValueLength()];
    copyValueTo(cell, output, 0);
    return output;
  }



  
  protected boolean queueHasRunnables() {
    return tableRunQueue.hasRunnables() || serverRunQueue.hasRunnables();
  }



  @VisibleForTesting
  // the chunks in the chunkIdMap may already be released so we shouldn't relay
  // on this counting for strong correctness. This method is used only in testing.
  int numberOfMappedChunks() {
    return this.chunkIdMap.size();
  }



  /** This is used to unescape text that's been escaped to prevent substitution of ${} expressions.
  */
  protected static String nonExpressionUnescape(String input) {
    // Not doing any escaping yet
    return input;
  }



  /** This is used to upgrade older constant values to new ones, that won't trigger expression eval.
  */
  protected static String nonExpressionEscape(String input) {
    // Not doing any escaping yet
    return input;
  }



  /** Get the variable's script value */
  public String getScriptValue()
    throws ScriptException
  {
    if (value)
      return "true";
    return "false";
  }



  /** Clear all document priorities for a job that is going to sleep */
  public void noDocPriorities(Long jobID)
    throws ManifoldCFException
  {
    HashMap map = new HashMap();
    map.put(needPriorityField,needPriorityToString(NEEDPRIORITY_FALSE));
    map.put(needPriorityProcessIDField,null);
    map.put(docPriorityField,nullDocPriority);
    ArrayList list = new ArrayList();
    String query = buildConjunctionClause(list,new ClauseDescription[]{
      new UnitaryClause(jobIDField,jobID)});
    performUpdate(map,"WHERE "+query,list,null);
    noteModifications(0,1,0);
  }



  /** Get the variable's script value */
  public String getScriptValue()
    throws ScriptException
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\"");
    int i = 0;
    while (i < value.length())
    {
      char x = value.charAt(i++);
      if (x == '\\' || x == '\"')
        sb.append('\\');
      sb.append(x);
    }
    sb.append("\"");
    return sb.toString();
  }



  /** Get the variable's script value */
  public String getScriptValue()
    throws ScriptException
  {
    return new Double(value).toString();
  }



  /** Get the variable's script value */
  public String getScriptValue()
    throws ScriptException
  {
    StringBuilder sb = new StringBuilder();
    sb.append("(new connectionname \"");
    int i = 0;
    while (i < connectionName.length())
    {
      char x = connectionName.charAt(i++);
      if (x == '\\' || x == '\"')
        sb.append('\\');
      sb.append(x);
    }
    sb.append("\")");
    return sb.toString();
  }



  /** Get the variable's script value */
  public String getScriptValue()
    throws ScriptException
  {
    return Integer.toString(value);
  }



	private void doSetDelegate(Object delegate) {
		Assert.notNull(delegate, "Delegate must not be null");
		this.delegate = delegate;
	}



	private QueueBuilder setDurable() {
		this.durable = true;
		return this;
	}



	
	public void doStop() {
		flush();
	}



	private MessageBatch doReleaseBatch() {
		if (this.messages.size() < 1) {
			return null;
		}
		Message message = assembleMessage();
		MessageBatch messageBatch = new MessageBatch(this.exchange, this.routingKey, message);
		this.messages.clear();
		this.currentSize = 0;
		this.exchange = null;
		this.routingKey = null;
		return messageBatch;
	}



    public void setRelationshipAttribute(String name, Object value) {
        Map<String, Object> r = this.relationshipAttributes;

        if (r != null) {
            r.put(name, value);
        } else {
            r = new HashMap<>();
            r.put(name, value);

            this.relationshipAttributes = r;
        }
    }



    public static String lower(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str.toLowerCase().trim();
    }



    protected APIInfo updatePathParameters(APIInfo apiInfo, String... params) {
        return new APIInfo(String.format(apiInfo.getPath(), params), apiInfo.getMethod(), apiInfo.getExpectedStatus());
    }



	private static String columnLabel(Path<?> path) {
		return path.toString();
	}



    /**
     * Private method that deals with disabling of REST support.
     *
     * @param response
     * @throws IOException
     */
    protected void showRestDisabledErrorMessage(HttpServletResponse response) throws IOException {
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        writer.println("<html><body><h2>Please enable REST support in WEB-INF/conf/axis2.xml " +
                       "and WEB-INF/web.xml</h2></body></html>");
        writer.flush();
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }



    public void addServiceGroupContextIntoSoapSessionTable(ServiceGroupContext serviceGroupContext) {
        String id = serviceGroupContext.getId();
        serviceGroupContextMap.put(id, serviceGroupContext);
        serviceGroupContext.touch();
        serviceGroupContext.setParent(this);
        // this is the best time to clean up the SGCtxts since are not being used anymore
        cleanupServiceGroupContexts();
    }



    /**
     * check if the pair is [UTF-16,UTF-16LE] [UTF-32, UTF-32LE],[UTF-16,UTF-16BE] [UTF-32,
     * UTF-32BE] etc.
     *
     * @param enc1 encoding style
     * @param enc2 encoding style
     * @return true if the encoding styles are compatible, or false otherwise
     */
    private static boolean compatibleEncodings(String enc1, String enc2) {
        enc1 = enc1.toLowerCase();
        enc2 = enc2.toLowerCase();
        if (enc1.endsWith("be") || enc1.endsWith("le")) {
            enc1 = enc1.substring(0, enc1.length() - 2);
        }
        if (enc2.endsWith("be") || enc2.endsWith("le")) {
            enc2 = enc2.substring(0, enc2.length() - 2);
        }
        return enc1.equals(enc2);
    }



    @Action(name="viewServiceGroupContext")
    public View viewServiceGroupContext(HttpServletRequest req) {
        String type = req.getParameter("TYPE");
        String sgID = req.getParameter("ID");
        ServiceGroupContext sgContext = configContext.getServiceGroupContext(sgID);
        req.getSession().setAttribute("ServiceGroupContext",sgContext);
        req.getSession().setAttribute("TYPE",type);
        req.getSession().setAttribute("ConfigurationContext",configContext);
        return new View("viewServiceGroupContext.jsp");
    }



	/**
	 * @param interfacesList  The interfacesList to set.
	 */
	public void setInterfacesList(List<String> interfacesList) {
		this.interfacesList = interfacesList;
	}



    public InvocationResponse doInvoke(MessageContext msgContext) throws AxisFault {
        // Get the list of headers for the roles we're acting in, then mark any we understand
        // as processed.
        MustUnderstandUtils.markUnderstoodHeaderParameters(msgContext);
        return InvocationResponse.CONTINUE;
    }



    public static void staticDataCleanup() {
        manager.spillables.clear();
        manager.accumulatedFreeSize = 0L;
    }



    private POSplit createSplit() {
        return new POSplit(new OperatorKey(scope, nig.getNextNodeId(scope)));
    }



	private void addToPlan(PhysicalOperator op) throws PlanException,
			IOException {
		SparkOperator sparkOp = null;
		if (compiledInputs.length == 1) {
			sparkOp = compiledInputs[0];
		} else {
			sparkOp = merge(compiledInputs);
		}
		sparkOp.physicalPlan.addAsLeaf(op);
		curSparkOp = sparkOp;
	}



    
    public boolean shouldOverwrite() {
        return this.overwriteOutput;
    }



    public static void staticDataCleanup() {
        tss = new ThreadLocal<UDFContext>() {
            
            public UDFContext initialValue() {
                return new UDFContext();
            }
        };
    }



    public SSLContextBuilder setProtocol(final String protocol) {
        this.protocol = protocol;
        return this;
    }



    /**
     * Returns available capacity of this buffer.
     *
     * @return buffer length.
     */
    public int capacity() {
        setInputMode();
        return this.buffer.remaining();
    }



    
    public String generateContent() {
        return content.toString();
    }



    public void setForkedExecutions( String projectKey, List<MojoExecution> forkedExecutions )
    {
        this.forkedExecutions.put( projectKey, forkedExecutions );
    }



    private MavenExecutionResult addExceptionToResult(MavenExecutionResult result, Throwable e)
    {
        if ( !result.getExceptions().contains( e ) )
        {
            result.addException( e );
        }

        return result;
    }



    /**
     * Calc processing stats
     */
    public boolean doStatistics() {
        return doProcessingStats;
    }



    public Jar openJar() throws IOException {
        if (entryName == null) {
            return null;
        } else {
            return JarFactory.newInstance(url);
        }
    }



    public AbstractRxTask createRxTask() {
        return getReplicationThread();
    }



    final synchronized void receivedEndOfStream() {
        stateChange(State.OPEN, State.HALF_CLOSED_REMOTE);
        stateChange(State.HALF_CLOSED_LOCAL, State.CLOSED_RX);
    }



    /**
     * <p>Authenticate the user making this request, based on the fact that no
     * <code>login-config</code> has been defined for the container.</p>
     *
     * <p>This implementation means "login the user even though there is no
     * self-contained way to establish a security Principal for that user".</p>
     *
     * <p>This method is called by the AuthenticatorBase super class to
     * establish a Principal for the user BEFORE the container security
     * constraints are examined, i.e. it is not yet known whether the user
     * will eventually be permitted to access the requested resource.
     * Therefore, it is necessary to always return <code>true</code> to
     * indicate the user has not failed authentication.</p>
     *
     * <p>There are two cases:</p>
     * <ul>
     * <li>without SingleSignon: a Session instance does not yet exist
     *     and there is no <code>auth-method</code> to authenticate the
     *     user, so leave Request's Principal as null.
     *     Note: AuthenticatorBase will later examine the security constraints
     *           to determine whether the resource is accessible by a user
     *           without a security Principal and Role (i.e. unauthenticated).
     * </li>
     * <li>with SingleSignon: if the user has already authenticated via
     *     another container (using its own login configuration), then
     *     associate this Session with the SSOEntry so it inherits the
     *     already-established security Principal and associated Roles.
     *     Note: This particular session will become a full member of the
     *           SingleSignOnEntry Session collection and so will potentially
     *           keep the SSOE "alive", even if all the other properly
     *           authenticated Sessions expire first... until it expires too.
     * </li>
     * </ul>
     *
     * @param request  Request we are processing
     * @param response Response we are creating
     * @return boolean to indicate whether the user is authenticated
     * @exception IOException if an input/output error occurs
     */
    
    protected boolean doAuthenticate(Request request, HttpServletResponse response)
        throws IOException {

        // Don't try and use SSO to authenticate since there is no auth
        // configured for this web application
        if (checkForCachedAuthentication(request, response, true)) {
            // save the inherited Principal in this session so it can remain
            // authenticated until it expires
            if (cache) {
                request.getSessionInternal(true).setPrincipal(request.getUserPrincipal());
            }
            return true;
        }

        // No Principal means the user is not already authenticated
        // and so will not be assigned any roles. It is safe to
        // to say the user is now authenticated because access to
        // protected resources will only be allowed with a matching role.
        // i.e. SC_FORBIDDEN (403 status) will be generated later.
        if (containerLog.isDebugEnabled())
            containerLog.debug("User authenticated without any roles");
        return true;
    }



    public synchronized void sentPushPromise() {
        stateChange(State.IDLE, State.RESERVED_LOCAL);
    }



    public synchronized void sentEndOfStream() {
        stateChange(State.OPEN, State.HALF_CLOSED_LOCAL);
        stateChange(State.HALF_CLOSED_REMOTE, State.CLOSED_TX);
    }



    public FilterRegistration getFilterRegistration(String filterName) {
        return null;
    }



    public ServletRegistration getServletRegistration(String servletName) {
        return null;
    }



    public void heartbeat() {
        if ( clusterSender!=null ) clusterSender.heartbeat();
        super.heartbeat();
    }



    
    public Enumeration<String> getAttributeNamesInScope(final int scope) {
        switch (scope) {
        case PAGE_SCOPE:
            return Collections.enumeration(attributes.keySet());

        case REQUEST_SCOPE:
            return request.getAttributeNames();

        case SESSION_SCOPE:
            if (session == null) {
                throw new IllegalStateException(Localizer.getMessage("jsp.error.page.noSession"));
            }
            return session.getAttributeNames();

        case APPLICATION_SCOPE:
            return context.getAttributeNames();

        default:
            throw new IllegalArgumentException("Invalid scope");
        }
    }



    public boolean isUseEquals() {
        return useEquals;
    }



    public static void set() {
        marker.set(Boolean.TRUE);
    }



    
    public PushBuilder path(String path) {
        if (path.startsWith("/")) {
            this.path = path;
        } else {
            String contextPath = baseRequest.getContextPath();
            int len = contextPath.length() + path.length() + 1;
            StringBuilder sb = new StringBuilder(len);
            sb.append(contextPath);
            sb.append('/');
            sb.append(path);
            this.path = sb.toString();
        }
        return this;
    }



    /**
     * TODO SERVLET 3.1
     */
    
    public boolean isReady() {
        // TODO Auto-generated method stub
        return false;
    }



    /**
     * Disable swallowing of remaining input if configured
     */
    protected void checkSwallowInput() {
        Context context = getContext();
        if (context != null && !context.getSwallowAbortedUploads()) {
            coyoteRequest.action(ActionCode.DISABLE_SWALLOW_INPUT, null);
        }
    }



    public AbstractRxTask createRxTask() {
        NioReplicationTask thread = new NioReplicationTask(this,this);
        thread.setUseBufferPool(this.getUseBufferPool());
        thread.setRxBufSize(getRxBufSize());
        thread.setOptions(getWorkerThreadOptions());
        return thread;
    }



    
    public final SocketState service(SocketWrapperBase<?> socketWrapper) throws IOException {
        return null;
    }



    
    protected void doClose() {
        channel.close();
    }



    /**
     * Obtain a builder for generating push requests. {@link PushBuilder}
     * documents how this request will be used as the basis for a push request.
     * Each call to this method will return a new instance, independent of any
     * previous instance obtained.
     *
     * @return A builder that can be used to generate push requests based on
     *         this request or {@code null} if push is not supported. Note that
     *         even if a PushBuilder instance is returned, by the time that
     *         {@link PushBuilder#push()} is called, it may no longer be valid
     *         to push a request and the push request will be ignored.
     *
     * @since Servlet 4.0
     */
    public default PushBuilder newPushBuilder() {
        return null;
    }



    
    protected InputStream doGetInputStream() {
        return null;
    }



    /**
     * Sets up the connection pool, by creating a pooling driver.
     * @return Driver
     * @throws SQLException
     */
    private synchronized ConnectionPool pCreatePool() throws SQLException {
        if (pool != null) {
            return pool;
        } else {
            pool = new ConnectionPool(poolProperties);
            return pool;
        }
    }



	@CliCommand(value = "solr setup", help = "Install support for Solr search integration")
	public void solrSetup(
		@CliOption(key = { "searchServerUrl" }, mandatory = false, unspecifiedDefaultValue = "http://localhost:8983/solr", specifiedDefaultValue = "http://localhost:8983/solr", help = "The URL of the Solr search server") final String searchServerUrl) {

		solrOperations.setupConfig(searchServerUrl);
	}



	@CliCommand(value = "solr add", help = "Make target type searchable")
	public void solrAdd(
		@CliOption(key = "class", mandatory = false, unspecifiedDefaultValue = "*", optionContext = "update,project", help = "The type to be made searchable") final JavaType javaType) {

		solrOperations.addSearch(javaType);
	}



	@CliCommand(value = "solr all", help = "Make all eligible project types searchable")
	public void solrAll() {
		solrOperations.addAll();
	}



	@CliCommand(value = "web jsf all", help = "Create JSF managed beans for all entities") 
	public void webJsfAll(
		@CliOption(key = "package", mandatory = true, optionContext = "update", help = "The package in which new JSF managed beans will be placed") JavaPackage destinationPackage) {
		
		jsfOperations.generateAll(destinationPackage);
	}



  public List<VariableDeclaration> getParameters() {
    return parameters;
  }



    public void addAutoscalingPolicy(String autoScalingPolicy) throws CommandException {
        restClient.deployEntity(ENDPOINT_DEPLOY_AUTOSCALING_POLICY, autoScalingPolicy, "autoscaling policy");
    }



    public void addCartridge(String cartridgeDefinition) throws CommandException {
        restClient.deployEntity(ENDPOINT_DEPLOY_CARTRIDGE, cartridgeDefinition, "cartridge");
    }



    public void addKubernetesCluster(String entityBody) {
        restClient.deployEntity(ENDPOINT_DEPLOY_KUBERNETES_CLUSTER, entityBody, "kubernetes cluster");
    }



    public void addCartridgeGroup(String entityBody) {
        restClient.deployEntity(ENDPOINT_DEPLOY_SERVICE_GROUP, entityBody, "service group");
    }



    public boolean addKubernetesHost(String kubernetesClusterId, KubernetesHost kubernetesHost)
            throws RemoteException, CloudControllerServiceInvalidKubernetesHostExceptionException,
            CloudControllerServiceNonExistingKubernetesClusterExceptionException {

        return stub.addKubernetesHost(kubernetesClusterId, kubernetesHost);
    }



    public boolean addAutoscalingPolicy(AutoscalePolicy autoScalePolicy) throws RemoteException,
            AutoscalerServiceInvalidPolicyExceptionException {
        return stub.addAutoScalingPolicy(autoScalePolicy);
    }



    /**
     * Generate kubernetes service id using clusterId, port protocol and port.
     * @param portMapping
     * @return
     */
    public static String generateKubernetesServiceId(String clusterId, PortMapping portMapping) {
        return String.format("%s-%s-%s", clusterId, portMapping.getProtocol(), portMapping.getPort());
    }



    public static boolean removeKubernetesHost(String kubernetesHostId) throws RestAPIException {

        CloudControllerServiceClient cloudControllerServiceClient = getCloudControllerServiceClient();
        if (cloudControllerServiceClient != null) {
            try {
                return cloudControllerServiceClient.undeployKubernetesHost(kubernetesHostId);

            } catch (RemoteException e) {
                log.error(e.getMessage(), e);
                throw new RestAPIException(e.getMessage(), e);
            } catch (CloudControllerServiceNonExistingKubernetesHostExceptionException e) {
                String message = e.getFaultMessage().getNonExistingKubernetesHostException().getMessage();
                log.error(message, e);
                throw new RestAPIException(message, e);
            }
        }
        return false;
    }



    /**
     * Prepare port name for port mapping.
     * @param portMapping
     * @return
     */
    public static String preparePortNameFromPortMapping(PortMapping portMapping) {
        return String.format("%s-%d", portMapping.getProtocol(), portMapping.getPort());
    }



    public boolean deployKubernetesCluster(KubernetesCluster kubernetesCluster) throws RemoteException,
            CloudControllerServiceInvalidKubernetesClusterExceptionException,
            CloudControllerServiceKubernetesClusterAlreadyExistsExceptionException {
        return stub.addKubernetesCluster(kubernetesCluster);
    }



    public boolean updateKubernetesCluster(KubernetesCluster kubernetesCluster) throws RemoteException,
            CloudControllerServiceInvalidKubernetesClusterExceptionException {
        return stub.updateKubernetesCluster(kubernetesCluster);
    }



	public boolean hasScalingDependants() {
		return hasScalingDependants;
	}



  /**
   * Iterate through the {@link JCas JCases} processed by the pipeline, allowing to access each one
   * after it has been processed.
   * 
   * @param aReader
   *          the collection reader.
   * @param aEngines
   *          the analysis engines.
   * @return an {@link Iterable}&lt;{@link JCas}&gt; which can be used in an extended for-loop.
   */
  public static JCasIterable iteratePipeline(final CollectionReaderDescription aReader,
          AnalysisEngineDescription... aEngines) {
    return new JCasIterable(aReader, aEngines);
  }



	
	protected void doHandleResult(AttributedCharSequence result) {
		terminal.writer().println(result.toAnsi(terminal));
		terminal.writer().flush();
	}



	@Bean
	@Qualifier("spring-shell")
	public ConversionService shellConversionService(ApplicationContext applicationContext) {
		Collection<Converter> converters = applicationContext.getBeansOfType(Converter.class).values();
		Collection<GenericConverter> genericConverters = applicationContext.getBeansOfType(GenericConverter.class).values();
		Collection<ConverterFactory> converterFactories = applicationContext.getBeansOfType(ConverterFactory.class).values();

		DefaultConversionService defaultConversionService = new DefaultConversionService();
		for (Converter converter : converters) {
			defaultConversionService.addConverter(converter);
		}
		for (GenericConverter genericConverter : genericConverters) {
			defaultConversionService.addConverter(genericConverter);
		}
		for (ConverterFactory converterFactory : converterFactories) {
			defaultConversionService.addConverterFactory(converterFactory);
		}
		return defaultConversionService;
	}



    /**
     * @return If true, then output two lines after multi-line statements.
     */
    public boolean isDoubleSpace() {
        return this.doubleSpace;
    }



    /**
     * @return If true, then try to parse multi-line SQL statements.
     */
    public boolean isMultiLine() {
        return this.multiLine;
    }



    boolean evalToBoolean(Environment env) {
        return val;
    }



    TemplateModel _eval(Environment env) {
        return val ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
    }



    /**
     * Makes the JavaBean properties of this object read-only.
     * 
     * @since 2.3.21
     */
    public void writeProtect() {
        readOnly = true;
    }



    TemplateModel _eval(Environment env) throws TemplateException {
        return new SequenceHash(env);
    }



    private Object ensureEvaled(Object value) throws _ObjectBuilderSettingEvaluationException {
        return value instanceof SettingExpression ? ((SettingExpression) value).eval() : value;
    }



    /**
     * Returns a {@link ClassIntrospectorBuilder}-s that could be used to create an identical {@link #ClassIntrospector}
     * . The returned {@link ClassIntrospectorBuilder} can be modified without interfering with anything.
     */
    ClassIntrospectorBuilder createBuilder() {
        return new ClassIntrospectorBuilder(this);
    }



    TemplateModel _eval(Environment env) {
        return new SimpleNumber(value);
    }



    public static boolean getDefaultLogTemplateExceptions(Version incompatibleImprovements) {
        return Configuration.getDefaultLogTemplateExceptions(incompatibleImprovements);
    }



    public static TemplateExceptionHandler getDefaultTemplateExceptionHandler(
            Version incompatibleImprovements) {
        return Configuration.getDefaultTemplateExceptionHandler(incompatibleImprovements);
    }



	/**
	 * default transition for drop partition in error state
	 * 
	 * @param message
	 * @param context
	 * @throws InterruptedException
	 */
  @Transition(to = "DROPPED", from = "ERROR")
  public void onBecomeDroppedFromError(Message message, NotificationContext context)
  {
    logger.info("Default ERROR->DROPPED transition invoked.");
  }



  public void rebalanceResource(String clusterName, String resourceName, int replica)
  {
    rebalanceStorageCluster(clusterName, resourceName, replica, resourceName); 
  }



  protected ResourceAssignment buildEmptyAssignment(String name,
      CurrentStateOutput currStateOutput) {
    ResourceAssignment assignment = new ResourceAssignment(name);
    Set<Partition> partitions = currStateOutput.getCurrentStateMappedPartitions(name);
    for (Partition partition : partitions) {
      Map<String, String> currentStateMap = currStateOutput.getCurrentStateMap(name, partition);
      Map<String, String> replicaMap = Maps.newHashMap();
      for (String instanceName : currentStateMap.keySet()) {
        replicaMap.put(instanceName, HelixDefinedState.DROPPED.toString());
      }
      assignment.addReplicaMap(partition, replicaMap);
    }
    return assignment;
  }



    public void init() throws Exception {
        tracker = new ServiceTracker(bundleContext, clazz.getName(), this);
        tracker.open();
    }



    /**
     * Set the management strategy
     *
     * @param managementStrategy the management strategy
     */
    public void bindManagementStrategy(ManagementStrategy managementStrategy) {
        this.managementStrategy = managementStrategy;
    }



    public void init() throws Exception {
        if (this.nmr == null) {
            throw new IllegalArgumentException("nmr must be set");
        }
        if (this.bundleContext == null) {
            throw new IllegalArgumentException("bundleContext must be set");
        }
        this.registry = new RegistryWrapper(nmr.getEndpointRegistry(), bundleContext);
    }



  
  public void insertTuple(int fieldId, Tuple tuple) {
    this.put(fieldId, tuple.asDatum(fieldId));
  }



  
  public void insertTuple(int fieldId, Tuple tuple) {
    this.put(fieldId, tuple.asDatum(fieldId));
  }



  
  public void insertTuple(int fieldId, Tuple tuple) {
    throw new TajoRuntimeException(new UnsupportedException());
  }



  public boolean isTypeParameterized() {
    return false;
  }



  
  public void insertTuple(int fieldId, Tuple tuple) {
    throw new TajoRuntimeException(new UnsupportedException());
  }



  public int bitsLength() {
    return length;
  }



  /**
   * Raw string for an identifier, which is equivalent to an identifier directly used in SQL statements.
   * @param policy IdentifierPolicy
   * @return Raw string
   */
  public String raw(IdentifierPolicy policy) {
    StringBuilder sb = new StringBuilder();
    if (quoted) {
      appendByCase(sb, policy.storesQuotedIdentifierAs());
      sb.insert(0, policy.getIdentifierQuoteString());
      sb.append(policy.getIdentifierQuoteString());
    } else {
      appendByCase(sb, policy.storesUnquotedIdentifierAs());
    }

    return sb.toString();
  }



  /**
   * Interned string of an identifier
   * @param policy IdentifierPolicy
   * @return interned string
   */
  public String interned(IdentifierPolicy policy) {
    StringBuilder sb = new StringBuilder();
    if (quoted) {
      appendByCase(sb, policy.storesQuotedIdentifierAs());
    } else {
      appendByCase(sb, policy.storesUnquotedIdentifierAs());
    }

    return sb.toString();
  }



  
  public TimeMeta asTimeMeta() {
    TimeMeta tm = new TimeMeta();
    DateTimeUtil.toJulianTimeMeta(timestamp, tm);

    return tm;
  }



  private boolean ensureColumnPartitionKeys(String tableName, String columnName) {
    final TableDesc tableDesc = catalog.getTableDesc(tableName);
    if (tableDesc.getPartitionMethod().getExpressionSchema().contains(columnName)) {
      return true;
    } else {
      return false;
    }
  }



  
  public void insertTuple(int fieldId, Tuple tuple) {
    throw new TajoRuntimeException(new UnsupportedException());
  }



  
  public Datum asDatum(int fieldId) {
    return values.get(fieldId);
  }



  
  public TimeMeta asTimeMeta() {
    TimeMeta tm = new TimeMeta();
    DateTimeUtil.date2j(time, tm);

    return tm;
  }



  
  public void insertTuple(int fieldId, Tuple tuple) {
    this.put(fieldId, tuple.asDatum(fieldId));
  }



	public String remove(String key) {
		return keyVals.remove(key);
	}



  
  public void insertTuple(int fieldId, Tuple tuple) {
    throw new UnsupportedOperationException();
  }



  
  public Datum[] getValues() {
    Datum[] datums = new Datum[values.length];
    for (int i = 0; i < values.length; i++) {
      datums[i] = get(i);
    }
    return datums;
  }



    protected ChannelInboundHandlerAdapter createChannelHandler() {
        return new NettyTcpTransportHandler();
    }



    protected boolean isNoLocal() {
        return this.consumerInfo.isNoLocal();
    }



    /**
     * Perform the close operation on the managed endpoint.  A subclass may
     * override this method to alter the standard close path such as endpoint
     * detach etc.
     */
    protected void closeOrDetachEndpoint() {
        getEndpoint().close();
    }



    
    protected void closeOrDetachEndpoint() {
        if (getResourceInfo().isDurable()) {
            getEndpoint().detach();
        } else {
            getEndpoint().close();
        }
    }



    
    protected ChannelInboundHandlerAdapter createChannelHandler() {
        return new NettyWebSocketTransportHandler();
    }



    
    public String getPath()
    {
        return this.path;
    }



    /**
     * Search the class hierarchy for a field with the given name. Will return
     * the nearest match, starting with the class specified and searching up the
     * hierarchy.
     *
     * @param clazz The class to search
     * @param name  The name of the field to search for
     * @return The field found, or null if no field is found
     */
    public static Field tryToFindDeclaredField(Class<?> clazz, String name)
    {
        for (Class<?> c = clazz; c != null && c != Object.class; c = c.getSuperclass())
        {
            try
            {
                return c.getDeclaredField(name);
            }
            catch (NoSuchFieldException e)
            {
                // No-op, we continue looking up the class hierarchy
            }
        }
        return null;
    }



    public MessageContext messageInterpolator(MessageInterpolator messageInterpolator)
    {
        this.messageInterpolator = messageInterpolator;
        return this;
    }



    public MessageContext messageResolver(MessageResolver messageResolver)
    {
        this.messageResolver = messageResolver;
        return this;
    }



    public MessageContext localeResolver(LocaleResolver localeResolver)
    {
        this.localeResolver = localeResolver;
        return this;
    }



    protected <X> Class<? extends Annotation> extractBindingClass(ProcessAnnotatedType<X> pat)
    {
        for (Annotation annotation : pat.getAnnotatedType().getAnnotations())
        {
            if (annotation.annotationType().isAnnotationPresent(PartialBeanBinding.class))
            {
                return annotation.annotationType();
            }
        }

        return null;
    }



    public boolean isBold() {
        return bold;
    }



    public boolean isItalics() {
        return italics;
    }



    
	public String apply(HasName arg) {
		return arg.getName();
	}



    
    public O apply(I arg) {
        return _response;
    }



    public boolean isExport() {
        return this.shouldExport;
    }



    public boolean isInherit() {
        return this.shouldInherit;
    }



    public void execute() throws MojoExecutionException, MojoFailureException {
        for (Dependency dependency: dependencies) {
            checkForMatch(dependency);
        }
    }



    protected void stop() {
        stop(bundleContext);        
    }



    protected void start() {
        start(bundleContext);
    }



    public boolean getIsVersionAtLeast(final int requiredVersion) {
        return SystemUtils.isJavaVersionAtLeast(requiredVersion);
    }



    public boolean getIsVersionAtLeast(final float requiredVersion) {
        return SystemUtils.isJavaVersionAtLeast(requiredVersion);
    }



    /**
     * Moves this GBeanInstance to the FAILED state.  There are no calls to dependent children, but they will be
     * notified using standard J2EE management notification.
     */
    final void referenceFailed() {
        gbeanInstanceState.fail();
    }



    public void rstopTest(boolean now) throws RemoteException {
        if (now) {
            log.info("Stopping test ...");
        } else {
            log.info("Shutting test ...");
        }
        backingEngine.stopTest(now);
        log.info("... stopped");
    }



    public void rreset() throws RemoteException, IllegalStateException {
        // Mail on userlist reported NPE here - looks like only happens if there are network errors, but check anyway
        if (backingEngine != null) {
            log.info("Reset");
            checkOwner("reset");
            backingEngine.reset();
        } else {
            log.warn("Backing engine is null, ignoring reset");
        }
    }



    public void rrunTest() throws RemoteException, JMeterEngineException, IllegalStateException {
        log.info("Running test");
        checkOwner("runTest");
        backingEngine.runTest();
    }



    private boolean isDelayedStartup() {
        return getPropertyAsBoolean(DELAYED_START);
    }



    public TestElement getTestElement()
    {
        return (TestElement) getUserObject();
    }



    /**
     * For tag keys, tag values always use a backslash character
     * \ to escape List of special characters : commas , equal sign = spaces
     */
    static final String tagToStringValue(String s) {
        return s.trim().replaceAll(" ", "\\\\ ").replaceAll(",", "\\\\,").replaceAll("=", "\\\\=");
    }



	public boolean isPerThread()
	{
		return getPropertyAsBoolean(PERTHREAD);
	}



	/**
	 * Return the number of records.
	 */
	public int getAuthCount() {
		return getAuthObjects().size();
	}



    public boolean isAlwaysEncoded()
    {
        return getPropertyAsBoolean(ALWAYS_ENCODE);
    }



  private String toStringEncNonAsciiCB(Collection<Bytes> columns) {
    return Iterators.toString(Iterators.transform(columns.iterator(), Hex::encNonAscii));
  }



  private String toStringEncNonAsciiCRC(Collection<RowColumn> ret) {
    return Iterators.toString(Iterators.transform(ret.iterator(), Hex::encNonAscii));
  }



  private String toStringEncNonAsciiCC(Collection<Column> columns) {
    return Iterators.toString(Iterators.transform(columns.iterator(), Hex::encNonAscii));
  }



  /**
   * @param initialCapacity The initial size of the byte builders internal array.
   */
  public static BytesBuilder builder(int initialCapacity) {
    return new BytesBuilder(initialCapacity);
  }



  /**
   * Provides an efficient and reusable way to build immutable Bytes objects.
   */
  public static BytesBuilder builder() {
    return new BytesBuilder();
  }



  /**
   * Returns the dictionary generated by this writer if one was created.
   * As part of this operation the dictionary is closed and will not have
   * any new values written into it.
   *
   * @return the dictionary page or null if not dictionary based
   */
  public DictionaryPage toDictPageAndClose() {
    return null;
  }



    public void saveJob(IDuccWorkJob duccWorkJob) throws IOException {
        String id = normalize(""+duccWorkJob.getDuccId().getFriendly());
        String fileName = historyDirectory_jobs+File.separator+id+"."+dwj;
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        fos = new FileOutputStream(fileName);
        out = new ObjectOutputStream(fos);
        out.writeObject(duccWorkJob);
        out.close();
    }



    public void saveReservation(IDuccWorkReservation duccWorkReservation) 
        throws Exception 
    {
        String id = normalize(""+duccWorkReservation.getDuccId().getFriendly());
        String fileName = historyDirectory_reservations+File.separator+id+"."+dwr;
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        fos = new FileOutputStream(fileName);
        out = new ObjectOutputStream(fos);
        out.writeObject(duccWorkReservation);
        out.close();
    }



	private static void setDisable() {
		disabled = true;
	}



	/**
	 * @return the name of the machine including the domain, if present
	 */
	public String getLongName() {
		return this.machine;
	}



   @Deprecated
   public boolean isAutoDeleteJmsQueues() {
      return autoDeleteJmsQueues != null ? autoDeleteJmsQueues : AddressSettings.DEFAULT_AUTO_DELETE_JMS_QUEUES;
   }



   private void reapplySettings() {
      for (PagingStore store : stores.values()) {
         AddressSettings settings = this.addressSettingsRepository.getMatch(store.getAddress().toString());
         store.applySetting(settings);
      }
   }



   public boolean isEnableMessageID() {
      return enableMessageID;
   }



   private void doIt() {
      try {
         if (logger.isDebugEnabled()) {
            logger.debug("deleting temporary queue " + queueName);
         }

         try {
            server.destroyQueue(queueName, null, false);
         } catch (ActiveMQException e) {
            ActiveMQServerLogger.LOGGER.errorOnDeletingQueue(queueName.toString(), e);
         }
      } catch (Exception e) {
         ActiveMQServerLogger.LOGGER.errorRemovingTempQueue(e, queueName);
      }
   }



   
   public void stop() throws Exception {
      stop(true);
   }



   
   public boolean reloadAddressInfo(AddressInfo addressInfo) throws Exception {
      return addressInfoMap.putIfAbsent(addressInfo.getName(), addressInfo) == null;
   }



    public synchronized void receivedEndOfStream() {
        stateChange(State.OPEN, State.HALF_CLOSED_REMOTE);
        stateChange(State.HALF_CLOSED_LOCAL, State.CLOSED_RX);
    }



    /**
     * TODO SERVLET 3.1
     */
    
    public boolean isReady() {
        // TODO Auto-generated method stub
        return false;
    }



    public void heartbeat() {
        if ( clusterSender!=null ) clusterSender.heartbeat();
        super.heartbeat();
    }



    
    public final SocketState service(SocketWrapperBase<?> socketWrapper) throws IOException {
        return null;
    }



    public ServletRegistration getServletRegistration(String servletName) {
        return null;
    }



    public FilterRegistration getFilterRegistration(String filterName) {
        return null;
    }



    /**
     * Calc processing stats
     */
    public boolean doStatistics() {
        return doProcessingStats;
    }



    /**
     * <p>Authenticate the user making this request, based on the fact that no
     * <code>login-config</code> has been defined for the container.</p>
     *
     * <p>This implementation means "login the user even though there is no
     * self-contained way to establish a security Principal for that user".</p>
     *
     * <p>This method is called by the AuthenticatorBase super class to
     * establish a Principal for the user BEFORE the container security
     * constraints are examined, i.e. it is not yet known whether the user
     * will eventually be permitted to access the requested resource.
     * Therefore, it is necessary to always return <code>true</code> to
     * indicate the user has not failed authentication.</p>
     *
     * <p>There are two cases:</p>
     * <ul>
     * <li>without SingleSignon: a Session instance does not yet exist
     *     and there is no <code>auth-method</code> to authenticate the
     *     user, so leave Request's Principal as null.
     *     Note: AuthenticatorBase will later examine the security constraints
     *           to determine whether the resource is accessible by a user
     *           without a security Principal and Role (i.e. unauthenticated).
     * </li>
     * <li>with SingleSignon: if the user has already authenticated via
     *     another container (using its own login configuration), then
     *     associate this Session with the SSOEntry so it inherits the
     *     already-established security Principal and associated Roles.
     *     Note: This particular session will become a full member of the
     *           SingleSignOnEntry Session collection and so will potentially
     *           keep the SSOE "alive", even if all the other properly
     *           authenticated Sessions expire first... until it expires too.
     * </li>
     * </ul>
     *
     * @param request  Request we are processing
     * @param response Response we are creating
     * @return boolean to indicate whether the user is authenticated
     * @exception IOException if an input/output error occurs
     */
    
    protected boolean doAuthenticate(Request request, HttpServletResponse response)
        throws IOException {

        // Don't try and use SSO to authenticate since there is no auth
        // configured for this web application
        if (checkForCachedAuthentication(request, response, true)) {
            // save the inherited Principal in this session so it can remain
            // authenticated until it expires
            if (cache) {
                request.getSessionInternal(true).setPrincipal(request.getUserPrincipal());
            }
            return true;
        }

        // No Principal means the user is not already authenticated
        // and so will not be assigned any roles. It is safe to
        // to say the user is now authenticated because access to
        // protected resources will only be allowed with a matching role.
        // i.e. SC_FORBIDDEN (403 status) will be generated later.
        if (containerLog.isDebugEnabled())
            containerLog.debug("User authenticated without any roles");
        return true;
    }



    /**
     * Disable swallowing of remaining input if configured
     */
    protected void checkSwallowInput() {
        Context context = getContext();
        if (context != null && !context.getSwallowAbortedUploads()) {
            coyoteRequest.action(ActionCode.DISABLE_SWALLOW_INPUT, null);
        }
    }



    public AbstractRxTask createRxTask() {
        NioReplicationTask thread = new NioReplicationTask(this,this);
        thread.setUseBufferPool(this.getUseBufferPool());
        thread.setRxBufSize(getRxBufSize());
        thread.setOptions(getWorkerThreadOptions());
        return thread;
    }



    public Jar openJar() throws IOException {
        if (entryName == null) {
            return null;
        } else {
            return JarFactory.newInstance(url);
        }
    }



    public static void set() {
        marker.set(Boolean.TRUE);
    }



    public synchronized void sentPushPromise() {
        stateChange(State.IDLE, State.RESERVED_LOCAL);
    }



    public synchronized void sentEndOfStream() {
        stateChange(State.OPEN, State.HALF_CLOSED_LOCAL);
        stateChange(State.HALF_CLOSED_REMOTE, State.CLOSED_TX);
    }



    /**
     * Sets up the connection pool, by creating a pooling driver.
     * @return Driver
     * @throws SQLException
     */
    private synchronized ConnectionPool pCreatePool() throws SQLException {
        if (pool != null) {
            return pool;
        } else {
            pool = new ConnectionPool(poolProperties);
            return pool;
        }
    }



    
    protected InputStream doGetInputStream() {
        return null;
    }



    
    protected void doClose() {
        channel.close();
    }



    public boolean isUseEquals() {
        return useEquals;
    }



    public AbstractRxTask createRxTask() {
        return getReplicationThread();
    }



    private Map<Capabilities, String> computeCapabilityMap(Session session) {
        Map<Capabilities, String> capabilities = Maps.newHashMap(capabilitiesBase);
        if (session.isAuthenticated()) {
            capabilities.put(Capabilities.OWNER, session.getUser());
        }
        return capabilities;
    }



    private Optional<MailboxId> readCreationIdAsMailboxId(MailboxCreationId creationId) {
        try {
            return Optional.of(mailboxIdFactory.fromString(creationId.getCreationId()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }



    public static Configuration defaultConfiguration() {
        return builder().build();
    }



    private static Stream<InternetAddress> streamOfInternetAddress(List<MailAddress> mailAddresses) {
        return mailAddresses.stream()
            .map(MailAddress::toInternetAddress);
    }



    public IndexCreationFactory useIndex(IndexName indexName) {
        Preconditions.checkNotNull(indexName);
        this.indexName = indexName;
        return this;
    }



    @VisibleForTesting void assertIsUserOwnerOfMailboxes(CreationMessageEntry entry, MailboxSession session) throws MailboxNotOwnedException {
        if (containsMailboxNotOwn(entry.getValue().getMailboxIds(), session)) {
            throw new MailboxNotOwnedException();
        }
    }



    public static <T> Optional<T> executeIfEmpty(Optional<T> optional, Operation operation) {
        if (!optional.isPresent()) {
            operation.perform();
        }
        return optional;
    }



    public UUID asUUID() {
        return token;
    }



    public String asString() {
        return namespace + ":" + user + ":" + name;
    }



    /**
     * Log in the user with the given userid and password
     *
     * @param userid the username
     * @param passwd the password
     * @return success true if login success false otherwise
     */
    private boolean isValidLogin(String userid, String passwd) throws MailboxException {
        return authenticator.isAuthentic(userid, passwd);
    }



  /**
     Are we running under JDK 1.x? 
          
   */
  public
  static
  boolean isJava1() {
    return java1;
  }



	@SuppressWarnings("unchecked")
	private List<String> timedout() {
		return (List<String>) namedQuery("timedout").execute();
	}



	
	protected int operatingCount() throws Exception {
		int total = 0;
		for (RemoteRunFactory rrf : factory.values())
			total += rrf.countOperatingRuns();
		return total;
	}



	@PreDestroy
	void removeAsSingleton() {
		installAsInstance(null);
		try {
			if (provider != null)
				removeProvider(provider.getName());
		} catch (SecurityException e) {
			log().warn(
					"failed to remove BouncyCastle security provider; "
							+ "might be OK if configured in environment", e);
		}
	}



	
	protected int operatingCount() throws Exception {
		return getFactory().countOperatingRuns();
	}



	
	@CallCounted
	public Response runBundleOptions() {
		return opt();
	}



    /**
     * Name the stream.
     * 
     * @param name
     *            the stream name, default is an empty string.
     * @return the stream maker object
     */
    public Stream<T> setName(String name) {
        this.name = name;
        return this;
    }



    private void refreshCluster() {
        // topology changes when processes pick tasks
        synchronized (nodes) {
            for (ClusterNode clusterNode : topology.getPhysicalCluster().getNodes()) {
                Integer partition = clusterNode.getPartition();
                nodes.forcePut(partition, clusterNode);
            }
        }

    }



    
    public CuratorFramework unwrap()
    {
        return client;
    }



    
    public ModelSpec<T> withPath(ZPath newPath)
    {
        return new ModelSpecImpl<>(newPath, serializer, createMode, aclList, createOptions, deleteOptions, ttl);
    }



    
    public ModeledCache<T> cache()
    {
        return cache;
    }



    
    public Listenable<ModeledCacheListener<T>> listenable()
    {
        return listenerContainer;
    }



    
    public ZPath child(Object child)
    {
        return new ZPathImpl(nodes, NodeName.nameFrom(child));
    }



	public void removeProcess(String processId) {
		processes.remove(processId);
	}



    public static GroovyMap crateGroovyMap(ProcessContext processContext)
            throws ApplicationSettingsException, AppCatalogException, GFacException {
        return createGroovyMap(processContext, null);
    }



    @Column(name = "NOTIFICATION_MESSAGE")
    public String getNotificationMessage() {
        return notificationMessage;
    }



	public boolean getIsDefaultQueue() {
		return isDefaultQueue;
	}



  static public String checksumFor(Path path, FileSystem fs) throws IOException {
    // TODO: fs checksum only available on hdfs, need to
    //       find a solution for other fs (eg, local fs, s3, etc)
    String checksumString = null;
    FileChecksum checksum = fs.getFileChecksum(path);
    if (checksum != null) {
      checksumString = StringUtils.byteToHexString(
          checksum.getBytes(), 0, checksum.getLength());
    }
    return checksumString;
  }



  public boolean getAllowDistinctFunctions() {
    return allowDistinctFunctions;
  }



  
  public JavaPairRDD<HiveKey, BytesWritable> doTransform(
      JavaPairRDD<BytesWritable, BytesWritable> input) {
    return input.mapPartitionsToPair(mapFunc);
  }



  /**
   * Not implemented.
   */
  
  public Object doDeserialize(Writable field) throws SerDeException {
    LOG.error("DelimitedJSONSerDe cannot deserialize.");
    throw new SerDeException("DelimitedJSONSerDe cannot deserialize.");
  }



  
  public boolean preferWritable() {
    return true;
  }



  @VisibleForTesting
  public boolean tryOffer(T t) {
    if (t == null || pool.length == 0) return false; // 0 size means no-pooling case - passthru.
    helper.resetBeforeOffer(t);
    return offerImpl(t);
  }



  /**
   * @return if the partition should only be added if it doesn't exist already
   */
  public boolean isIfNotExists() {
    return this.ifNotExists;
  }



  
  public boolean incRefBuffer(MemoryBuffer buffer) {
    // notifyReused implies that buffer is already locked; it's also called once for new
    // buffers that are not cached yet. Don't notify cache policy.
    return lockBuffer(((LlapDataBuffer)buffer), false);
  }



  
  public void decRefBuffer(MemoryBuffer buffer) {
    unlockBuffer((LlapDataBuffer)buffer, true);
  }



  
  public void decRefBuffers(List<MemoryBuffer> cacheBuffers) {
    for (MemoryBuffer b : cacheBuffers) {
      unlockBuffer((LlapDataBuffer)b, true);
    }
  }



  public HashTableKind getHashTableKind() {
    return hashTableKind;
  }



  public boolean getMinMaxEnabled() {
    return minMaxEnabled;
  }



  public HashTableImplementationType getHashTableImplementationType() {
    return hashTableImplementationType;
  }



  public HashTableKeyType getHashTableKeyType() {
    return hashTableKeyType;
  }



  /**
   * Deserialize a row from the Writable to a LazyObject.
   *
   * @param field
   *          the Writable that contains the data
   * @return The deserialized row Object.
   * @see SerDe#deserialize(Writable)
   */
  
  public Object doDeserialize(Writable field) throws SerDeException {
    if (byteArrayRef == null) {
      byteArrayRef = new ByteArrayRef();
    }
    BinaryComparable b = (BinaryComparable) field;
    byteArrayRef.setData(b.getBytes());
    cachedLazyStruct.init(byteArrayRef, 0, b.getLength());
    lastOperationSerialize = false;
    lastOperationDeserialize = true;
    return cachedLazyStruct;
  }



  public void notifyOfClusterStateChange() {
    currentLock.lock();
    try {
      current.hasClusterStateChanged = true;
      notifyWmThreadUnderLock();
    } finally {
      currentLock.unlock();
    }
  }



  
  public boolean preferWritable() {
    return false;
  }



  /**
   * ROO-3709: Indicator that checks if exists some project setting that makes
   * each of the following parameters mandatory: sequenceName, generationType, identifierColumn and table
   * 
   * @param shellContext
   * @return true if exists property
   *         {@link #SPRING_ROO_JPA_REQUIRE_SCHEMA_OBJECT_NAME} on project settings
   *         and its value is "true". If not, return false.
   */
  @CliOptionMandatoryIndicator(params = {"sequenceName", "generationType", "identifierColumn",
      "table"}, command = "entity jpa")
  public boolean areSchemaObjectNamesRequired(ShellContext shellContext) {

    // Check if property 'spring.roo.jpa.require.schema-object-name' is defined on
    // project settings
    String requiredSchemaObjectName =
        projectSettings.getProperty(SPRING_ROO_JPA_REQUIRE_SCHEMA_OBJECT_NAME);

    if (requiredSchemaObjectName != null && requiredSchemaObjectName.equals("true")) {
      return true;
    }

    return false;
  }



    
    public boolean arePropertiesCommandAvailable() {
        return projectOperations.isFocusedProjectAvailable();
    }



  /**
   * Makes 'class' option visible only if 'all' option is not specified.
   * 
   * @param shellContext
   * @return false if 'all' is specified, true otherwise.
   */
  @CliOptionVisibilityIndicator(command = "entity projection", params = {"class"},
      help = "Option 'class' can't be used with 'all' option in the same command.")
  public boolean isClassVisibleForEntityProjection(ShellContext shellContext) {

    // Check already specified params
    Map<String, String> params = shellContext.getParameters();
    if (params.containsKey("all")) {
      return false;
    }

    return true;
  }



  public JpaEntityMetadata getEntityMetadata() {
    return entityMetadata;
  }



    public <T> T calculate(Supplier<T> action) {
        return getTransactionHandler().calculate(action) ;
    }



    
    public Graph getGraph(Node graphNode)
    {
        return new GraphSDB(store, graphNode) ;
    }



    
    public final boolean test(T item)
    {
        if ( subFilterLast )
            return acceptAdditionaOther(item) ;
        else
            return acceptOtherAdditional(item) ;
    }



    
    public void print(char[] cbuf)
    {
        try { writer.write(cbuf) ; } catch (IOException ex) { IO.exception(ex) ; }
    }



    
    public void print(String string)
    { 
        try { writer.write(string) ; } catch (IOException ex) { IO.exception(ex) ; }
    }



    /**
     * A {@link DatasetGraph} may contain other <strong>wrapped DatasetGraph's</strong>. This method will return
     * the first instance (including the argument to this method) that <strong>is not</strong> an instance of
     * {@link DatasetGraphWrapper}.
     *
     * @param dsg a {@link DatasetGraph}
     * @return the first found {@link DatasetGraph} that is not an instance of {@link DatasetGraphWrapper}
     */
    // Unused currently.
    private static DatasetGraph x_unwrap(DatasetGraph dsg) {
        while (dsg instanceof DatasetGraphWrapper) {
            dsg = ((DatasetGraphWrapper)dsg).getWrapped() ;
        }
        return dsg ;
    }



    protected void exec(Item item)
    {
        if ( ! print )
            return ;
        
        if ( item == null )
        {
            System.err.println("No expression") ;
            throw new TerminationException(9) ;
        }
        divider() ;
        IndentedWriter out = new IndentedWriter(System.out, lineNumbers) ;
        
        // Need to check if used.
        //PrefixMapping pmap = SSE.getDefaultPrefixMapWrite() ;
        PrefixMapping pmap = null ;
        SerializationContext sCxt = new SerializationContext(pmap) ;
        ItemWriter.write(out, item, sCxt) ;
        //item.output(out) ;
        out.ensureStartOfLine() ;
        out.flush();
    }



    
    public void accept(Printable item)
    {
        if ( ! first && sep != null )
            out.print(sep) ;
        first = false ;
        item.output(out) ;
    }



    /** Produce a {@link RDF_PrefixName} is possible. */ 
    private static RDF_PrefixName abbrev(String uriStr, PrefixMap pmap) {
        if ( pmap == null )
            return null ;
        Pair<String, String> p = pmap.abbrev(uriStr) ;
        if ( p == null )
            return null ;
        return new RDF_PrefixName(p.getLeft(), p.getRight()) ;
    }



    /** Block until no writers are active.
     *  When this returns, yhis guarantees that the database is not changing
     *  and the jounral is flush to disk.
     * <p> 
     * The application must call {@link #enableWriters} later.
     * <p> 
     * This operation must not be nested (it will block).
     * 
     * @see #tryBlockWriters()
     * @see #enableWriters()
     * 
     */
    public void blockWriters() {
        acquireWriterLock(true) ;
    }



    
    public Node apply(Node node)
    {
        if ( ! node.isBlank() )
            return node ;
        Node node2 = mapping.get(node) ;
        if ( node2 == null )
        {
            Var v = varAlloc.allocVar() ;
            mapping.put(node, v) ;
            node2 = v ;
        }
        return node2 ;
    }



    /** return true if dispatched */
    private boolean serviceDispatch(HttpAction action, ServiceRef service, String srvName , SPARQL_ServletBase servlet)
    {
        if ( ! service.endpoints.contains(srvName) )
            return false ;
        servlet.executeLifecycle(action) ;
        return true ;
    }



    protected final void emitTriple(Node subject, Node predicate, Node object) {
        emit(subject, predicate, object) ;
    }



    
    public void accept(T item)
    { count++ ; }



	
	public boolean test( final Statement o )
	{
		final Property p = o.getPredicate();
		if (p.getNameSpace().equals(RDF.getURI())
				&& p.getLocalName().startsWith("_"))
		{
			try
			{
				Integer.parseInt(p.getLocalName().substring(1));
				return true;
			}
			catch (final NumberFormatException e)
			{
				// acceptable;
			}
		}
		return false;
	}



    private static QueryIterator evalGroundedPath(Binding binding, 
                                                  Graph graph, Node subject, Path path, Node object,
                                                  ExecutionContext execCxt) {
        Iterator<Node> iter = PathEval.eval(graph, subject, path, execCxt.getContext()) ;
        // Now count the number of matches.
        
        int count = 0 ;
        for ( ; iter.hasNext() ; )
        {
            Node n = iter.next() ;
            if ( n.sameValueAs(object) )
                count++ ;
        }
        
        return new QueryIterYieldN(count, binding, execCxt) ;
    }



    private static QueryIterator evalGroundedOneEnd(Binding binding, Iterator<Node> iter, Node endNode, ExecutionContext execCxt) {
        List<Binding> results = new ArrayList<>() ;
        
        if (! Var.isVar(endNode))
            throw new ARQInternalErrorException("Non-variable endnode in _execTriplePath") ;
        
        Var var = Var.alloc(endNode) ;
        // Assign.
        for (; iter.hasNext();) {
            Node n = iter.next() ;
            results.add(BindingFactory.binding(binding, var, n)) ;
        }
        return new QueryIterPlainWrapper(results.iterator(), execCxt) ;
    }



    /**
     * Ensure that the algebra op is a filter. If the input is a filter, just return that,
     * else create a filter with no expressions and "this" as the subOp.
     * @apiNote
     * This operation assumes the caller is going to add expressions. 
     * Filters without any expressions are discouraged.
     * Consider collecting the expressions together first and using {@link #filterBy}. 
     */
    public static OpFilter ensureFilter(Op op) {
        if ( op instanceof OpFilter )
            return (OpFilter)op ;
        else
            return new OpFilter(op) ;
    }



	
	public boolean test(T o) {
		boolean retval = !seen.contains(o);
		if (retval)
		{
			seen.add( o );
		}
		return retval;
	}



    /** Execute and return a value in a read transaction */
    public static <T extends Transactional, X> X calculateRead(T txn, Supplier<X> r) {
        boolean b = txn.isInTransaction() ;
        if ( !b )
            txn.begin(ReadWrite.READ) ;
        try {
            X x = r.get() ;
            if ( !b )
                txn.end() ;
            return x ;
        } catch (Throwable th) {
            onThrowable(th, txn);
            throw th ;
        }
    }



    /** return whether caching is on of off */
    public boolean isCachingModels() { return cacheModelLoads ; }



    /** Lookup a {@linkplain Lang} to get the registered {@linkplain ResultSetReaderFactory} (or null) */
    public static ResultSetReaderFactory getFactory(Lang lang) {
        Objects.requireNonNull(lang) ;
        return registry.get(lang) ;
    }



    private WriterGraphRIOT writer() {
        if ( writer != null )
            return writer;
        if ( jenaName == null )
            throw new IllegalArgumentException("Jena writer name is null");
        // For writing via model.write(), use the old names for jena writers.
        RDFFormat format = RDFWriterRegistry.getFormatForJenaWriter(jenaName) ;
        if ( format != null )
            return RDFDataMgr.createGraphWriter(format) ;
        Lang lang = RDFLanguages.nameToLang(jenaName);
        if ( lang != null )
            return RDFDataMgr.createGraphWriter(lang);
        throw new RiotException("No graph writer for '" + jenaName + "'");
    }



    public static String maskPassword( Commandline cl )
    {
        String clString = cl.toString();

        int pos = clString.indexOf( '@' );

        if ( pos > 0 )
        {
            clString = clString.replaceAll( ":\\w+@", ":*****@" );
        }

        return clString;
    }



    public static Settings readSettings()
    {
        File settingsFile = getSettingsFile();

        if ( settingsFile.exists() )
        {
            SvnXpp3Reader reader = new SvnXpp3Reader();
            try
            {
                return reader.read( ReaderFactory.newXmlReader( settingsFile ) );
            }
            catch ( FileNotFoundException e )
            {
                //Nothing to do
            }
            catch ( IOException e )
            {
                //Nothing to do
            }
            catch ( XmlPullParserException e )
            {
                String message = settingsFile.getAbsolutePath() + " isn't well formed. SKIPPED." + e.getMessage();

                System.err.println( message );
            }
        }

        return new Settings();
    }



  /**
   * Read parameters from context
   * <li>-maxTotalEvents = type long that defines the total number of Events to be sent
   * <li>-maxSuccessfulEvents = type long that defines the number of successful Events
   * <li>-size = type int that defines the number of bytes in each Event
   * <li>-batchSize = type int that defines the number of Events being sent in one batch
   */
  
  protected void doConfigure(Context context) throws FlumeException {
    /* Limit on the total number of events. */
    maxTotalEvents = context.getLong("maxTotalEvents", -1L);
    /* Limit on the total number of successful events. */
    maxSuccessfulEvents = context.getLong("maxSuccessfulEvents", -1L);
    /* Set max events in a batch submission */
    batchSize = context.getInteger("batchSize", 1);
    /* Size of events to be generated. */
    int size = context.getInteger("size", 500);

    prepEventData(size);
  }



  @VisibleForTesting
  protected boolean didHitChannelException() {
    return hitChannelException;
  }



	
	protected Set<String> extractActualHeaders(Operation operation) {
		return operation.getRequest().getHeaders().keySet();
	}



	private String removeQueryStringIfPresent(String urlTemplate) {
		int index = urlTemplate.indexOf('?');
		if (index == -1) {
			return urlTemplate;
		}
		return urlTemplate.substring(0, index);
	}



	
	protected Set<String> extractActualHeaders(Operation operation) {
		return operation.getResponse().getHeaders().keySet();
	}



  
  public Vector viewRow(int row) {
    if (row < 0 || row >= rowSize()) {
      throw new IndexException(row, rowSize());
    }
    return new DenseVector(values[row], true);
  }



  public double getSD() {
    return Math.sqrt(variance);
  }



  public double getMean() {
    return mean;
  }



  public int getCount() {
    return n;
  }



  /**
   * Returns a default command line option for specification of OUTLIER THRESHOLD value. Used for
   * Cluster Classification.
   */
  public static DefaultOptionBuilder outlierThresholdOption() {
    return new DefaultOptionBuilder()
        .withLongName(OUTLIER_THRESHOLD)
        .withRequired(false)
        .withArgument(
            new ArgumentBuilder().withName(OUTLIER_THRESHOLD).withMinimum(1)
                .withMaximum(1).create()).withDescription("Outlier threshold value")
        .withShortName(OUTLIER_THRESHOLD);
  }



  public float alphaI() {
    return alphaI;
  }



    private int updateSql(Connection conn, String sql) throws SQLException {
        DBDictionary dict = _conf.getDBDictionaryInstance();
        PreparedStatement stmnt = null;
        int rc = -1;
        try {
            stmnt = conn.prepareStatement(sql);
            dict.setTimeouts(stmnt, _conf, false);
            rc = stmnt.executeUpdate();
        } catch (Exception e) {
            // tolerate exception when attempting to alter increment,
            // however, caller should check rc and not cache sequence values if rc != -1.
        } finally {
            // clean up our resources
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException se) {
                }
            }
        }
        return rc;
    }



    private void commonLock(OpenJPAStateManager sm, int level, int timeout,
            Object sdata, boolean postLockVersionCheck) {
        if (level == LOCK_NONE)
            return;
        while (sm.getOwner() != null)
            sm = sm.getOwner();
        int oldLevel = getLockLevel(sm);
        if (!sm.isPersistent() || sm.isNew() || level <= oldLevel)
            return;

        try {
            lockInternal(sm, level, timeout, sdata, postLockVersionCheck);
        } catch (RuntimeException re) {
            // revert lock
            setLockLevel(sm, oldLevel);
            throw re;
        }
    }



    /**
     * Returns a new predicate as the negation of this predicate. 
     * <br>
     * Note:
     * Default negation creates a Not expression with this receiver as delegate.
     * Derived predicates can return the inverse expression, if exists.
     * For example, NotEqual for Equal or LessThan for GreaterThanEqual etc.
     */
    public PredicateImpl not() {
        return new Expressions.Not(this).markNegated();
    }



    private void initializeMetaDataFactory() {
        if (_factory == null) {
            MetaDataFactory mdf = _conf.newMetaDataFactoryInstance();
            if (mdf == null)
                throw new MetaDataException(_loc.get("no-metadatafactory"));
            setMetaDataFactory(mdf);
        }
    }



    /**
     * Whether if map and collection in entity are stored as blob.
     * Defaults to <code>false</code>.
     *
     * @since 1.1.0 
     */

    public boolean getStoreMapCollectionInEntityAsBlob() {
        return _storeMapCollectionInEntityAsBlob;
    }



    /**
     * Log a message.   
     */
    private static void log(String msg) {
        // at this point logging isn't configured yet
        System.err.println(msg);
    }



	
	protected Message<?> receiveMessage() {
		return this.source.receive();
	}



	protected final void doSetWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		Assert.notNull(webServiceTemplate, "'webServiceTemplate' must not be null");
		this.webServiceTemplate = webServiceTemplate;
		this.webServiceTemplateExplicitlySet = true;
	}



	protected final void doSetFilter(FileListFilter<F> filter) {
		this.filter = filter;
	}



	protected final void doSetFilter(FileListFilter<F> filter) {
		this.filter = filter;
	}



	protected final void doSetRemoteDirectoryExpression(Expression remoteDirectoryExpression) {
		Assert.notNull(remoteDirectoryExpression, "'remoteDirectoryExpression' must not be null");
		this.remoteDirectoryExpression = remoteDirectoryExpression;
	}



	
	protected void postProcessClientAfterConnect(FTPSClient ftpsClient) throws IOException {
		ftpsClient.execPBSZ(0);
		ftpsClient.execPROT(this.prot);
	}



	public PriorityChannelSpec capacity(int capacity) {
		this.capacity = capacity;
		return this;
	}



	public PriorityChannelSpec comparator(Comparator<Message<?>> comparator) {
		this.comparator = comparator;
		return this;
	}



	private void doSetLevel(Level level) {
		Assert.notNull(level, "'level' cannot be null");
		this.level = level;
	}



	
	protected void doStop() {
		if (this.amqpTemplate instanceof Lifecycle) {
			((Lifecycle) this.amqpTemplate).stop();
		}
	}



	/**
	 * @return whether a reply Message is expected.
	 * @see AbstractHttpRequestExecutingMessageHandler#setExpectReply(boolean)
	 */
	public boolean isExpectReply() {
		return this.expectReply;
	}



    
    protected void doInitialize(Object pojo, String memento) {
        final ViewModel viewModel = (ViewModel) pojo;
        viewModel.viewModelInit(memento);
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            contributed = Contributed.AS_ASSOCIATION
    )
    @PropertyLayout(
            named = "Version",
            hidden = Where.ALL_TABLES
    )
    @MemberOrder(name = "Metadata", sequence = "800.2")
    public java.sql.Timestamp prop() {
        final Object version = JDOHelper.getVersion(persistable);
        return version != null && version instanceof java.sql.Timestamp ? (java.sql.Timestamp) version : null;
    }



    
    protected void doInitialize(final Object pojo, final String memento) {
        final RecreatableDomainObject viewModel = (RecreatableDomainObject)pojo;
        viewModel.__isis_recreate(memento);
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            contributed = Contributed.AS_ASSOCIATION
    )
    @PropertyLayout(
            named = "Id",
            hidden = Where.ALL_TABLES
    )
    @MemberOrder(name = "Metadata", sequence = "800.1")
    public Long prop() {
        final Object objectId = JDOHelper.getObjectId(persistable);
        if(objectId instanceof DatastoreId) {
            final DatastoreId datastoreId = (DatastoreId) objectId;
            final Object id = datastoreId.getKeyAsObject();
            return id != null && id instanceof Long ? (Long) id : null;
        }
        return null;
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION,
            cssClassFa = "fa-download",
            position = ActionLayout.Position.PANEL_DROPDOWN
    )
    @MemberOrder(name = "datanucleusIdLong", sequence = "700.1")
    public Object act(
            @ParameterLayout(named = "File name")
            final String fileName,
            final LayoutService.Style style) {
        final String xml = layoutService.toXml(object.getClass(), style);

        return new Clob(Util.withSuffix(fileName, style.name().toLowerCase() + ".xml"), "text/xml", xml);
    }



    protected SessionLoggingService getSessionLoggingService() {
        return getIsisSessionFactory().getServicesInjector().lookupService(SessionLoggingService.class);
    }



    private void updateSubclasses(final ObjectSpecification subclass) {
        this.subclasses.addSubclass(subclass);
    }



    public void endTransaction() {
        final javax.jdo.Transaction transaction = persistenceManager.currentTransaction();
        if (transaction.isActive()) {
            transaction.commit();
        }
    }



    public void abortTransaction() {
        final javax.jdo.Transaction transaction = persistenceManager.currentTransaction();
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }



    public void startTransaction() {
        final javax.jdo.Transaction transaction = persistenceManager.currentTransaction();
        if (transaction.isActive()) {
            throw new IllegalStateException("Transaction already active");
        }
        transaction.begin();
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION,
            cssClassFa = "fa-bookmark"
    )
    public Object act() {
        return bookmarkService.lookup(bookmarkHolder);
    }



    
    public int getNumberCreated() {
        return numAdaptersOfKind(PublishedObject.ChangeKind.CREATE);
    }



    
    public int getNumberUpdated() {
        return numAdaptersOfKind(PublishedObject.ChangeKind.UPDATE);
    }



    
    public int getNumberDeleted() {
        return numAdaptersOfKind(PublishedObject.ChangeKind.DELETE);
    }



    @Action(
            semantics = SemanticsOf.SAFE,
            domainEvent = BookmarkHolder_object.ActionDomainEvent.class
    )
    @ActionLayout(
        contributed = Contributed.AS_ASSOCIATION
    )
    public Object prop() {
        return bookmarkService.lookup(bookmarkHolder);
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION,
            cssClassFa = "fa-download"
    )
    @MemberOrder(sequence = "500.1")
    public Object act(
            @ParameterLayout(named = "File name")
            final String fileName) {

        final String xml = jaxbService.toXml(dto);
        return new Clob(Util.withSuffix(fileName, "xml"), "text/xml", xml);
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION,
            cssClassFa = "fa-download",
            position = ActionLayout.Position.PANEL_DROPDOWN
    )
    @MemberOrder(name = "datanucleusIdLong", sequence = "710.1")
    public Clob act(
            @ParameterLayout(named = "File name")
            final String fileName) throws JAXBException, IOException {

        final Class<? extends Persistable> objClass = persistable.getClass();
        final String objClassName = objClass.getName();

        final TypeMetadata metadata = getPersistenceManagerFactory().getMetadata(objClassName);
        final String xml = metadata.toString();

        return new Clob(Util.withSuffix(fileName, "jdo"), "text/xml", xml);
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.IDEMPOTENT
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION,
            cssClassFa = "fa-circle-o",
            position = ActionLayout.Position.PANEL_DROPDOWN
    )
    @MemberOrder(name = "datanucleusIdLong", sequence = "400.1")
    public Object act() {
        if (getHintStoreUsingWicketSession() != null) {
            final Bookmark bookmark = bookmarkService.bookmarkFor(object);
            getHintStoreUsingWicketSession().removeAll(bookmark);
        }
        return object;
    }



    private List objectActionsOf(final ObjectSpecification specification) {
        return specification.getObjectActions(ActionType.ALL, Contributed.INCLUDED, Filters.<ObjectAction>any());
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            contributed = Contributed.AS_ASSOCIATION
    )
    @PropertyLayout(
            named = "Version",
            hidden = Where.ALL_TABLES
    )
    @MemberOrder(name = "Metadata", sequence = "800.2")
    public Long prop() {
        final Object version = JDOHelper.getVersion(persistable);
        return version != null && version instanceof Long ? (Long) version : null;
    }



    public static String memberIdentifierFor(final ObjectMember objectMember) {
        return objectMember.getIdentifier().toClassAndNameIdentityString();
    }



    @Action(
            domainEvent = AllConfigurationPropertiesDomainEvent.class,
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            cssClassFa = "fa-wrench"
    )
    @MemberOrder(sequence = "500.900.1")
    public Set<ConfigurationProperty> configuration(){
        return configurationService.allProperties();
    }



    private <T> T valueFor(final String parameterName, final ExecutionContext ec, final T defaultValue) {
        final Class<T> cls = (Class<T>) defaultValue.getClass();

        final T value = readParam(parameterName, ec, cls);
        if(value != null) { return (T) value; }

        // else default value
        return defaultValue;
    }



    private static <T extends Enum<T>> int maxLengthFor(final Class<T> adaptedClass) {
        int max = Integer.MIN_VALUE;
        for(T e: adaptedClass.getEnumConstants()) {
            final int nameLength = e.name().length();
            final int toStringLength = e.toString().length();
            max = Math.max(max, Math.max(nameLength, toStringLength));
        }
        return max;
    }



    public void notifyOnChange(final ScalarModelSubscriber subscriber) {
        subscribers.add(subscriber);
    }



    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.IDEMPOTENT,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION,
            cssClassFa = "fa-refresh",
            position = ActionLayout.Position.PANEL_DROPDOWN
    )
    @MemberOrder(name = "datanucleusIdLong", sequence = "800.1")
    public void act() {
        metaModelService.rebuild(object.getClass());
    }



    /** {@inheritDoc} */
     public long getEstimatedRebalancingFinishTime() {
        long rate = rebalancingKeysRate.getRate();

        return rate <= 0 ? -1L :
            ((getKeysToRebalanceLeft() / rate) * REBALANCE_RATE_INTERVAL) + U.currentTimeMillis();
    }



    /**
     * @return Name of task class that triggered the event.
     */
    public String getTaskClassName() {
        return taskClsName;
    }



    /**
     * @return Whether task was created for system needs.
     */
    public boolean isInternal() {
        return internal;
    }



    /**
     * @return Name of the task that triggered the event.
     */
    public String getTaskName() {
        return taskName;
    }



    /**
     * @return Task session ID.
     */
    public IgniteUuid getTaskSessionId() {
        return taskSesId;
    }



    /** {@inheritDoc} */
     public void shutdown() {
        for (MappedFile file : mappedFiles) {
            try {
                file.close();
            }
            catch (IOException e) {
                log.error("Failed to close memory-mapped file upon stop (will ignore) [file=" +
                    file.file() + ", err=" + e.getMessage() + ']');
            }
        }
    }



    /** {@inheritDoc} */
     public byte cacheObjectType() {
        return TYPE_BYTE_ARR;
    }



    /**
     * @return Errors count.
     */
    public long getErrorCount() {
        return errCnt;
    }



    /**
     * @param topVer New topology version value.
     */
    public void setTopologyVersion(long topVer) {
        this.topVer = topVer;
    }



    /**
     * @return Current task monitoring state.
     */
    public boolean isTaskMonitoringEnabled() {
        return taskMonitoringEnabled;
    }



    /**
     * @param errCnt Errors count.
     */
    public void setErrorCount(long errCnt) {
        this.errCnt = errCnt;
    }



    /**
     * @return Current topology version.
     */
    public long getTopologyVersion() {
        return topVer;
    }



    /**
     * @param taskMonitoringEnabled New value of task monitoring state.
     */
    public void setTaskMonitoringEnabled(boolean taskMonitoringEnabled) {
        this.taskMonitoringEnabled = taskMonitoringEnabled;
    }



    /**
     * @return Initial exchange version.
     */
    public AffinityTopologyVersion initialVersion() {
        return exchId.topologyVersion();
    }



    /** {@inheritDoc} */
    @Nullable  public DiscoverySpiCustomMessage ackMessage() {
        DiscoveryCustomMessage res = delegate.ackMessage();

        return res == null ? null : new CustomMessageWrapper(res);
    }



    /**
     * Formats the file system removing all existing entries from it.
     *
     * @return Future.
     */
    IgniteInternalFuture<?> clearAsync0() {
        GridFutureAdapter<?> fut = new GridFutureAdapter<>();

        Thread t = new Thread(new FormatRunnable(fut), "igfs-format-" + cfg.getName() + "-" +
            FORMAT_THREAD_IDX_GEN.incrementAndGet());

        t.setDaemon(true);

        t.start();

        return fut;
    }



    /** {@inheritDoc} */
     public void clear() {
        try {
            IgniteUuid id = meta.format();

            // If ID is null, then file system is already empty.
            if (id == null)
                return;

            while (true) {
                if (enterBusy()) {
                    try {
                        if (!meta.exists(id))
                            return;
                    }
                    finally {
                        busyLock.leaveBusy();
                    }
                }

                U.sleep(10);
            }
        }
        catch (Exception e) {
            throw IgfsUtils.toIgfsException(e);
        }
    }



    /**
     * @return Near cache eviction policy max size.
     */
    @Nullable public Integer getNearEvictMaxSize() {
        return nearEvictMaxSize;
    }



    /**
     * @return Near cache start size.
     */
    public int getNearStartSize() {
        return nearStartSize;
    }



    /**
     * @return Near cache eviction policy.
     */
    @Nullable public String getNearEvictPolicy() {
        return nearEvictPlc;
    }



    /**
     * @return {@code true} if near cache enabled.
     */
    public boolean isNearEnabled() {
        return nearEnabled;
    }



    /**
     * @return Peer-to-peer pool size.
     */
    public int getPeerClassLoadingThreadPoolSize() {
        return p2pPoolSz;
    }



    /**
     * @return IGFS pool size.
     */
    public int getIgfsThreadPoolSize() {
        return igfsPoolSize;
    }



    /**
     * @return Management pool size.
     */
    public int getManagementThreadPoolSize() {
        return mgmtPoolSize;
    }



    /**
     * @return REST requests pool size.
     */
    public int getRestThreadPoolSize() {
        return restPoolSz;
    }



    /**
     * @return Public pool size.
     */
    public int getPublicThreadPoolSize() {
        return pubPoolSize;
    }



    /**
     * @return System pool size.
     */
    public int getSystemThreadPoolSize() {
        return sysPoolSz;
    }



    /**
     * @return {@code True} if entry was filtered.
     */
    boolean isFiltered() {
        return (flags & FILTERED_ENTRY) != 0;
    }



    /** {@inheritDoc} */
     public byte cacheObjectType() {
        return TYPE_REGULAR;
    }



    /**
     * @return Deployment alias.
     */
    public String getAlias() {
        return alias;
    }



    /**
     * Normalizes given path for windows.
     * Log4j2 doesn't replace unix directory delimiters which used at 'fileName' to windows.
     *
     * @param path Path.
     * @return Normalized path.
     */
    private String normalize(String path) {
        if (!U.isWindows())
            return path;

        return path.replace('/', File.separatorChar);
    }



    /**
     * Kernal stop callback.
     *
     * @param err Error.
     */
    public void cancelFutures(IgniteCheckedException err) {
        stopErr = err;

        for (AffinityReadyFuture fut : readyFuts.values())
            fut.onDone(err);
    }



    /**
     * @return {@code true} if Visor should collect information about tasks.
     */
    public boolean isTaskMonitoringEnabled() {
        return taskMonitoringEnabled;
    }



    /**
     * @return Key for store and read last event order number.
     */
    public String getEventsOrderKey() {
        return evtOrderKey;
    }



    /**
     * @param sysCaches {@code true} if Visor should collect metrics for system caches.
     */
    public void setSystemCaches(boolean sysCaches) {
        this.sysCaches = sysCaches;
    }



    /**
     * @return Key for store and read events throttle counter.
     */
    public String getEventsThrottleCounterKey() {
        return evtThrottleCntrKey;
    }



    /**
     * @param taskMonitoringEnabled If {@code true} then Visor should collect information about tasks.
     */
    public void setTaskMonitoringEnabled(boolean taskMonitoringEnabled) {
        this.taskMonitoringEnabled = taskMonitoringEnabled;
    }



    /**
     * @return {@code true} if Visor should collect metrics for system caches.
     */
    public boolean getSystemCaches() {
        return sysCaches;
    }



    /**
     * @param evtOrderKey Key for store and read last event order number.
     */
    public void setEventsOrderKey(String evtOrderKey) {
        this.evtOrderKey = evtOrderKey;
    }



    /**
     * @param evtThrottleCntrKey Key for store and read events throttle counter.
     */
    public void setEventsThrottleCounterKey(String evtThrottleCntrKey) {
        this.evtThrottleCntrKey = evtThrottleCntrKey;
    }



    /** {@inheritDoc} */
     public UUID dataStructureId() {
        return matrixId;
    }



    /**
     * @return Number of cache entries stored in off-heap memory.
     */
    public long getOffHeapEntriesCount() {
        return offHeapEntriesCnt;
    }



    /** {@inheritDoc} */
     public CacheMetrics localMetrics() {
        return new CacheMetricsSnapshot(metrics);
    }



    /**
     * @param lex Lexer.
     */
    private void parseIndexColumn(SqlLexer lex) {
        String name = parseIdentifier(lex);
        boolean desc = false;

        SqlLexerToken nextToken = lex.lookAhead();

        if (matchesKeyword(nextToken, ASC) || matchesKeyword(nextToken, DESC)) {
            lex.shift();

            if (matchesKeyword(lex, DESC))
                desc = true;
        }

        addColumn(lex, new SqlIndexColumn(name, desc));
    }



    /**
     * @return Rebalance partitioned delay.
     */
    public long getPartitionedDelay() {
        return partitionedDelay;
    }



    /**
     * @return Cache rebalance mode.
     */
    public CacheRebalanceMode getMode() {
        return mode;
    }



    /**
     * @return Time in milliseconds to wait between rebalance messages.
     */
    public long getThrottle() {
        return throttle;
    }



    /**
     * @return Cache rebalance batch size.
     */
    public int getBatchSize() {
        return batchSize;
    }



    /**
     * @return Rebalance timeout.
     */
    public long getTimeout() {
        return timeout;
    }



    /**
     * Creates and starts store session listeners.
     *
     * @param ctx Kernal context.
     * @param factories Factories.
     * @return Listeners.
     * @throws IgniteCheckedException In case of error.
     */
    public static Collection<CacheStoreSessionListener> startStoreSessionListeners(GridKernalContext ctx,
        Factory<CacheStoreSessionListener>[] factories) throws IgniteCheckedException {
        if (factories == null)
            return null;

        Collection<CacheStoreSessionListener> lsnrs = new ArrayList<>(factories.length);

        for (Factory<CacheStoreSessionListener> factory : factories) {
            CacheStoreSessionListener lsnr = factory.create();

            if (lsnr != null) {
                ctx.resource().injectGeneric(lsnr);

                if (lsnr instanceof LifecycleAware)
                    ((LifecycleAware)lsnr).start();

                lsnrs.add(lsnr);
            }
        }

        return lsnrs;
    }



    /** {@inheritDoc} */
    @Nullable  public GridNioRecoveryDescriptor inRecoveryDescriptor() {
        return null;
    }



    /** {@inheritDoc} */
     public AffinityTopologyVersion readyTopologyVersion() {
        lock.readLock().lock();

        try {
            assert topVer.topologyVersion() > 0;

            return topVer;
        }
        finally {
            lock.readLock().unlock();
        }
    }



    /**
     * @return Concurrency level.
     */
    public int getConcurrencyLevel() {
        return concLvl;
    }



    /** {@inheritDoc} */
     public void onClose() {
        platformCtx.gateway().messagingFilterDestroy(hnd);
    }



    /**
     * @param cls Component interface.
     * @return Name of component implementation class for open source edition.
     */
    private static String componentClassName(Class<?> cls) {
        return cls.getPackage().getName() + ".os." + cls.getSimpleName().replace("Grid", "GridOs");
    }



    /** {@inheritDoc} */
    @Nullable  public DiscoveryCustomMessage ackMessage() {
        return null;
    }



    /**
     * @return Items.
     */
    public Collection<?> getItems() {
        return items;
    }



    /**
     * @param items Items.
     */
    public void setItems(Collection<?> items) {
        this.items = items;
    }



    /**
     * @return Last flag.
     */
    public boolean getLast() {
        return last;
    }



    /**
     * @param last Last flag.
     */
    public void setLast(boolean last) {
        this.last = last;
    }



    /**
     * @return Query ID.
     */
    public long getQueryId() {
        return qryId;
    }



    /**
     * @param qryId Query ID.
     */
    public void setQueryId(long qryId) {
        this.qryId = qryId;
    }



    /**
     * @return File path relative to the search folder.
     */
    public String getFilePath() {
        return filePath;
    }



    /**
     * @return Line number in the file, 1 based.
     */
    public int getLineNumber() {
        return lineNum;
    }



    /**
     * @return File size.
     */
    public long getFileSize() {
        return fileSize;
    }



    /**
     * @return Node ID.
     */
    public UUID getNid() {
        return nid;
    }



    /**
     * @return Lines count in the file.
     */
    public int getLineCount() {
        return lineCnt;
    }



    /**
     * @return Timestamp of last modification of the file.
     */
    public long getLastModified() {
        return lastModified;
    }



    /**
     * @return File content encoding.
     */
    public String getEncoding() {
        return encoding;
    }



    /**
     * @return Cache affinity.
     */
    public String getFunction() {
        return function;
    }



    /**
     * @return Number of backup nodes for one partition.
     */
    public int getPartitionedBackups() {
        return partitionedBackups;
    }



    /**
     * @return Cache affinity mapper.
     */
    public String getMapper() {
        return mapper;
    }



    /**
     * @return Cache size in bytes.
     */
    public long getMemorySize() {
        return memorySize;
    }



    /**
     * @return {@code true} if cache has near cache.
     */
    public boolean isNear() {
        return near;
    }



    /**
     * @return Number of backup entries in cache.
     */
    public long getBackupSize() {
        return backupSize;
    }



    /**
     * @return Number of primary entries in cache.
     */
    public long getPrimarySize() {
        return primarySize;
    }



    /**
     * @return Number of all entries in near cache.
     */
    public int getNearSize() {
        return nearSize;
    }



    /**
     * @return Dynamic deployment ID.
     */
    public IgniteUuid getDynamicDeploymentId() {
        return dynamicDeploymentId;
    }



    /**
     * @return Cache mode.
     */
    public CacheMode getMode() {
        return mode;
    }



    /**
     * @return Number of all entries in cache.
     */
    public long getSize() {
        return size;
    }



    /**
     * @return Cache metrics.
     */
    public VisorCacheMetrics getMetrics() {
        return metrics;
    }



    /**
     * @return Number of partitions.
     */
    public int getPartitions() {
        return partitions;
    }



    /**
     * Sets new value for cache name.
     *
     * @param name New cache name.
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * @return Indexes size in bytes.
     */
    public long getIndexesSize() {
        return indexesSize;
    }



    /**
     * @return Cache name.
     */
    public String getName() {
        return name;
    }



    /**
     * @return Matrix elements storage mode.
     */
    public int storageMode() {
        return stoMode;
    }



    /**
     *
     */
    public void onConnected() {
        synchronized (this) {
            assert reserved : this;
            assert !connected : this;

            connected = true;

            if (handshakeReq != null) {
                IgniteInClosure<Boolean> c = handshakeReq.get2();

                assert c != null;

                c.apply(false);

                handshakeReq = null;
            }

            notifyAll();
        }
    }



    /** {@inheritDoc} */
     public GridCacheVersion conflictVersion() {
        return drVer;
    }



    /**
     * @return Whether peer-to-peer class loading is enabled.
     */
    public boolean isPeerClassLoadingEnabled() {
        return p2pEnabled;
    }



    /**
     * @return Missed resource cache size.
     */
    public int getPeerClassLoadingMissedResourcesCacheSize() {
        return p2pMissedResCacheSize;
    }



    /**
     * @return Node address that caused this event to be generated.
     */
    public String getAddress() {
        return addr;
    }



    /**
     * @return Event node ID.
     */
    public UUID getEventNodeId() {
        return evtNodeId;
    }



    /**
     * @return Topology version or {@code 0} if configured discovery SPI implementation
     *      does not support versioning.
     **/
    public long getTopologyVersion() {
        return topVer;
    }



    /** {@inheritDoc} */
     protected GridSqlElement column(int col) {
        throw new IllegalStateException();
    }



    /**
     * @return Task argument.
     */
    public A getArgument() {
        return arg;
    }



    /**
     * @return Debug flag.
     */
    public boolean isDebug() {
        return debug;
    }



    /**
     * @param cacheName Cache name.
     * @param k         Key into the cache.
     * @param <K>       Key type.
     * @return Cluster group for given key.
     */
    protected static <K> ClusterGroup getClusterGroupForGivenKey(String cacheName, K k) {
        return ignite().cluster().forNode(ignite().affinity(cacheName).mapKeyToNode(k));
    }



    /**
     * @return Columns.
     */
    public Collection<DbColumn> getColumns() {
        return cols;
    }



    /**
     * @return Schema name.
     */
    public String getSchema() {
        return schema;
    }



    /**
     * @return Table name.
     */
    public String getTable() {
        return tbl;
    }



    /**
     * @return Nodes.
     */
    public Collection<UUID> getNodes() {
        return metrics.keySet();
    }



    /** @return Cache mode. */
    public CacheMode getMode() {
        return mode;
    }



    /** @return Cache system state. */
    public boolean isSystem() {
        return sys;
    }



    /**
     * @return Map of Node IDs to cache metrics.
     */
    public Map<UUID, VisorCacheMetrics> getMetrics() {
        return metrics;
    }



    /**
     * @return Cache name.
     */
    public String getName() {
        return name;
    }



    /** {@inheritDoc} */
    @Nullable  public DiscoveryCustomMessage ackMessage() {
        return null;
    }



    /**
     * @return Task session ID of the task that triggered the event.
     */
    public IgniteUuid getTaskSessionId() {
        return taskSesId;
    }



    /**
     * @return Job ID.
     */
    public IgniteUuid getJobId() {
        return jobId;
    }



    /**
     * @return Name of task class that triggered the event.
     */
    public String getTaskClassName() {
        return taskClsName;
    }



    /**
     * @return Name of the task that triggered the event.
     */
    public String getTaskName() {
        return taskName;
    }



    /** {@inheritDoc} */
     public int internalSize() {
        return 0;
    }



    /** {@inheritDoc} */
     public void shutdown() {
        for (Iterator<DirectMemoryRegion> it = regions.iterator(); it.hasNext(); ) {
            DirectMemoryRegion chunk = it.next();

            GridUnsafe.freeMemory(chunk.address());

            // Safety.
            it.remove();
        }
    }



    /**
     * Commit ended.
     */
    public void resetContext() {
        threadCtx.set(null);
    }



    /**
     * @return Field type name.
     */
    public String getFieldTypeName() {
        return fieldTypeName;
    }



    /**
     * @return Type name.
     */
    public String getTypeName() {
        return typeName;
    }



    /**
     * @return Field name.
     */
    public String getFieldName() {
        return fieldName;
    }



    /**
     * @param schema If {@code true} then add schema name to full name.
     * @return Fully qualified field name with type name and schema name.
     */
    public String getFullName(boolean schema) {
        if (!F.isEmpty(typeName)) {
            if (schema && !F.isEmpty(schemaName))
                return schemaName + "." + typeName + "." + fieldName;

            return typeName + "." + fieldName;
        }

        return fieldName;
    }



    /**
     * @return Schema name.
     */
    public String getSchemaName() {
        return schemaName;
    }



    /**
     * @param topVer Finished exchange topology version.
     */
    public void flushBackupQueue(AffinityTopologyVersion topVer) {
        for (CacheContinuousQueryListener lsnr : lsnrs.values())
            lsnr.flushBackupQueue(cctx.kernalContext(), topVer);
    }



    /**
     * @param active active New value of grid active flag.
     */
    public void setActive(boolean active) {
        this.active = active;
    }



    /**
     * @return {@code True} if grid is active.
     */
    public boolean isActive() {
        return active;
    }



    public void initialUpdateCounter(long val) {
        store.updateInitialCounter(val);
    }



    /**
     * @return Lifecycle beans.
     */
    @Nullable public String getBeans() {
        return beans;
    }



    /**
     * @return {@code true} if this column belongs to primary key.
     */
    public boolean isKey() {
        return key;
    }



    /**
     * @return {@code true} if column is unsigned.
     */
    public boolean isUnsigned() {
        return unsigned;
    }



    /**
     * @return Column JDBC type.
     */
    public int getType() {
        return type;
    }



    /**
     * @return {@code true} if {@code NULL } allowed for column in database.
     */
    public boolean isNullable() {
        return nullable;
    }



    /**
     * @return Column name.
     */
    public String getName() {
        return name;
    }



    /**
     * @return Conflict version.
     */
    @Nullable public GridCacheVersion conflictVersion() {
        return this; // Use current version.
    }



    /**
     * @return Grid.
     */
    protected Ignite ignite() {
        return node.ignite();
    }



    /** {@inheritDoc} */
     public BinaryType type(String typeName) throws BinaryObjectException {
        throw unsupported();
    }



    /** {@inheritDoc} */
     public BinaryType type(int typeId) throws BinaryObjectException {
        throw unsupported();
    }



    /** {@inheritDoc} */
     public BinaryType type(Class<?> cls) throws BinaryObjectException {
        throw unsupported();
    }



    /** {@inheritDoc} */
     public Collection<BinaryType> types() throws BinaryObjectException {
        throw unsupported();
    }



    /** {@inheritDoc} */
     public String igfsName() {
        return ggfsName;
    }



    /**
     *
     */
    public void cleanCachesAndGroups() {
        registeredCacheGrps.clear();
        registeredCaches.clear();
    }



    /**
     * @return Jetty host.
     */
    @Nullable public String getJettyHost() {
        return jettyHost;
    }



    /**
     * @return Jetty port.
     */
    @Nullable public Integer getJettyPort() {
        return jettyPort;
    }



    /**
     * @return Jetty config path.
     */
    @Nullable public String getJettyPath() {
        return jettyPath;
    }



    /**
     * @return Context factory for SSL.
     */
    @Nullable public String getTcpSslContextFactory() {
        return tcpSslCtxFactory;
    }



    /**
     * @return Whether REST enabled or not.
     */
    public boolean isRestEnabled() {
        return restEnabled;
    }



    /**
     * @return Whether or not SSL is enabled for TCP binary protocol.
     */
    public boolean isTcpSslEnabled() {
        return tcpSslEnabled;
    }



    /**
     * @return REST TCP binary host.
     */
    @Nullable public String getTcpHost() {
        return tcpHost;
    }



    /**
     * Gets grid instance associated with this actor.
     *
     * @return Grid instance associated with this actor.
     */
    protected final Ignite ignite() {
        assert ignite != null;

        return ignite;
    }



    /**
     * @return Whether no discovery order is allowed.
     */
    public boolean isNoDiscoOrder() {
        return noDiscoOrder;
    }



    /**
     * @return Network timeout.
     */
    public long getNetworkTimeout() {
        return netTimeout;
    }



    /**
     * @return Client mode flag.
     */
    public Boolean isClientMode() {
        return clientMode;
    }



    /**
     * @return Whether update checker is enabled.
     */
    public boolean isUpdateNotifier() {
        return updateNtf;
    }



    /**
     * @return Whether this node daemon or not.
     */
    public boolean isDaemon() {
        return daemon;
    }



    /**
     * @return Local host value used.
     */
    @Nullable public String getLocalHost() {
        return locHost;
    }



    /**
     * @return IGNITE_HOME determined at startup.
     */
    @Nullable public String getGgHome() {
        return ggHome;
    }



    /**
     * @return MBean server name
     */
    @Nullable public String getMBeanServer() {
        return mBeanSrv;
    }



    /**
     * @return Marshaller used.
     */
    public String getMarshaller() {
        return marsh;
    }



    /**
     * @return Whether remote JMX is enabled.
     */
    public boolean isJmxRemote() {
        return jmxRemote;
    }



    /**
     * @return Whether shutdown hook is disabled.
     */
    public boolean isNoShutdownHook() {
        return noShutdownHook;
    }



    /**
     * @return Name of command line program.
     */
    public String getProgramName() {
        return progName;
    }



    /**
     * @return Logger used on node.
     */
    public String getLogger() {
        return log;
    }



    /**
     * @return Success file name.
     */
    public String getSuccessFile() {
        return successFile;
    }



    /**
     * @return Discovery startup delay.
     */
    public long getDiscoStartupDelay() {
        return discoStartupDelay;
    }



    /**
     * @return Whether node is in quiet mode.
     */
    public boolean isQuiet() {
        return quiet;
    }



    /**
     * @return Ignite instance name.
     */
    @Nullable public String getIgniteInstanceName() {
        return igniteInstanceName;
    }



    /**
     * @return Whether ASCII logo is disabled.
     */
    public boolean isNoAscii() {
        return noAscii;
    }



    /**
     * @return Is node restart enabled.
     */
    public boolean isRestart() {
        return restart;
    }



    /**
     * @return Transactions configuration.
     */
    public VisorTransactionConfiguration getTransaction() {
        return txCfg;
    }



    /**
     * @return Lifecycle.
     */
    public VisorLifecycleConfiguration getLifecycle() {
        return lifecycle;
    }



    /**
     * @return Rest.
     */
    public VisorRestConfiguration getRest() {
        return rest;
    }



    /**
     * @return Memory configuration.
     */
    public VisorMemoryConfiguration getMemoryConfiguration() {
        return memCfg;
    }



    /**
     * @return Include events types.
     */
    public int[] getIncludeEventTypes() {
        return inclEvtTypes;
    }



    /**
     * @return Basic.
     */
    public VisorBasicConfiguration getBasic() {
        return basic;
    }



    /**
     * @return Metrics.
     */
    public VisorMetricsConfiguration getMetrics() {
        return metrics;
    }



    /**
     * @return SPIs.
     */
    public VisorSpisConfiguration getSpis() {
        return spis;
    }



    /**
     * @return Executors service configuration.
     */
    public VisorExecutorServiceConfiguration getExecutorService() {
        return execSvc;
    }



    /**
     * @return System properties.
     */
    public Properties getSystemProperties() {
        return sysProps;
    }



    /**
     * @return User attributes.
     */
    public Map<String, ?> getUserAttributes() {
        return userAttrs;
    }



    /**
     * @return Environment.
     */
    public Map<String, String> getEnv() {
        return env;
    }



    /**
     * @return Configuration of atomic data structures.
     */
    public VisorAtomicConfiguration getAtomic() {
        return atomic;
    }



    /**
     * @return P2P.
     */
    public VisorPeerToPeerConfiguration getP2p() {
        return p2p;
    }



    /**
     * @return Segmentation.
     */
    public VisorSegmentationConfiguration getSegmentation() {
        return seg;
    }



    /**
     * @return Include properties.
     */
    public String getIncludeProperties() {
        return inclProps;
    }



    /**
     * @return Reads per second.
     */
    public int getReadsPerSecond() {
        return readsPerSec;
    }



    /**
     * @return Cache name.
     */
    public String getName() {
        return name;
    }



    /**
     * @return Number of cached rolled back transaction IDs.
     */
    public int getTxRolledbackVersionsSize() {
        return txRolledbackVersionsSize;
    }



    /**
     * @return The mean time to execute removes.
     */
    public float getAvgRemovalTime() {
        return avgRemovalTime;
    }



    /**
     * @return Gets transaction per-thread map size.
     */
    public int getTxThreadMapSize() {
        return txThreadMapSize;
    }



    /**
     * @return Cache mode.
     */
    public CacheMode getMode() {
        return mode;
    }



    /**
     * @return Current size of evict queue used to batch up evictions.
     */
    public int getDhtEvictQueueCurrentSize() {
        return dhtEvictQueueCurrSize;
    }



    /**
     * @return Removes per second.
     */
    public int getRemovalsPerSecond() {
        return removalsPerSec;
    }



    /**
     * @return Start version counts map size.
     */
    public int getTxStartVersionCountsSize() {
        return txStartVerCountsSize;
    }



    /**
     * @return Total number of transaction commits.
     */
    public long getTxCommits() {
        return txCommits;
    }



    /**
     * @return avgTxCommitTime
     */
    public float getAvgTxCommitTime() {
        return avgTxCommitTime;
    }



    /**
     * @return Memory size allocated in off-heap.
     */
    public long getOffHeapAllocatedSize() {
        return offHeapAllocatedSize;
    }



    /**
     * @return The mean time to execute tx rollbacks.
     */
    public float getAvgTxRollbackTime() {
        return avgTxRollbackTime;
    }



    /**
     * @return Puts per second.
     */
    public int getPutsPerSecond() {
        return putsPerSec;
    }



    /**
     * @return Number of cached committed transaction IDs.
     */
    public int getTxCommittedVersionsSize() {
        return txCommittedVersionsSize;
    }



    /**
     * @return The total number of puts to the cache.
     */
    public long getPuts() {
        return puts;
    }



    /**
     * @return Committed DHT transaction queue size.
     */
    public int getTxDhtCommitQueueSize() {
        return txDhtCommitQueueSize;
    }



    /**
     * @return DHT thread map size
     */
    public int getTxDhtThreadMapSize() {
        return txDhtThreadMapSize;
    }



    /**
     * @return The total number of removals from the cache.
     */
    public long getRemovals() {
        return removals;
    }



    /**
     * @return Total number of writes of the owning entity (either cache or entry).
     */
    public long getWrites() {
        return writes;
    }



    /**
     * @return Prepared transaction queue size.
     */
    public int getTxPrepareQueueSize() {
        return txPrepareQueueSize;
    }



    /**
     * @return DHT start version counts map size.
     */
    public int getTxDhtStartVersionCountsSize() {
        return txDhtStartVerCountsSize;
    }



    /**
     * @return Committed transaction queue size.
     */
    public int getTxCommitQueueSize() {
        return txCommitQueueSize;
    }



    /**
     * @return Cache system state.
     */
    public boolean isSystem() {
        return sys;
    }



    /**
     * @return Total number of transaction rollbacks.
     */
    public long getTxRollbacks() {
        return txRollbacks;
    }



    /**
     * @return The mean time to execute puts.
     */
    public float getAvgPutTime() {
        return avgPutTime;
    }



    /**
     * @return Transaction DHT per-Xid map size.
     */
    public int getTxDhtXidMapSize() {
        return txDhtXidMapSize;
    }



    /**
     * @return Commits per second.
     */
    public int getCommitsPerSecond() {
        return commitsPerSec;
    }



    /**
     * @return Total number of reads of the owning entity (either cache or entry).
     */
    public long getReads() {
        return reads;
    }



    /**
     * @return Total number of misses for the owning entity (either cache or entry).
     */
    public long getMisses() {
        return misses;
    }



    /**
     * @return Gets number of keys in the cache, possibly with {@code null} values.
     */
    public int getKeySize() {
        return keySize;
    }



    /**
     * @return The mean time to execute gets
     */
    public float getAvgReadTime() {
        return avgReadTime;
    }



    /**
     * @return The total number of evictions from the cache.
     */
    public long getEvictions() {
        return evictions;
    }



    /**
     * @return Number of non-{@code null} values in the cache.
     */
    public int getSize() {
        return size;
    }



    /**
     * @return Prepared DHT transaction queue size.
     */
    public int getTxDhtPrepareQueueSize() {
        return txDhtPrepareQueueSize;
    }



    /**
     * Sets cache name.
     *
     * @param name New value for cache name.
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * @return Rollbacks per second.
     */
    public int getRollbacksPerSecond() {
        return rollbacksPerSec;
    }



    /**
     * @return Transaction per-Xid map size.
     */
    public int getTxXidMapSize() {
        return txXidMapSize;
    }



    /**
     * @return Number of cached committed DHT transaction IDs.
     */
    public int getTxDhtCommittedVersionsSize() {
        return txDhtCommittedVersionsSize;
    }



    /**
     * @return Number of cached rolled back DHT transaction IDs.
     */
    public int getTxDhtRolledbackVersionsSize() {
        return txDhtRolledbackVersionsSize;
    }



    /**
     * @return Total number of hits for the owning entity (either cache or entry).
     */
    public long getHits() {
        return hits;
    }



    /**
     * Index of the item inside of data page.
     *
     * @param link Page link.
     * @return Offset in 8-byte words.
     */
    public static int itemId(long link) {
        return (int)((link >> (PAGE_IDX_SIZE + RESERVED_SIZE + FILE_ID_SIZE)) & OFFSET_MASK);
    }



    /**
     * @return All task monitoring state collected from nodes.
     */
    public Map<UUID, Boolean> getTaskMonitoringEnabled() {
        return taskMonitoringEnabled;
    }



    /**
     * @param cacheId Cache ID to check.
     * @return {@code True} if cache is stopping by this exchange.
     */
    private boolean cacheStopping(int cacheId) {
        return exchActions != null && exchActions.cacheStopped(cacheId);
    }



    /**
     * @return File last modified timestamp.
     */
    public long getLastModified() {
        return lastModified;
    }



    /**
     * @return File path.
     */
    public String getPath() {
        return path;
    }



    /**
     * @return File size.
     */
    public long getSize() {
        return size;
    }



    /**
     * @return Pessimistic log cleanup delay in milliseconds.
     */
    public int getPessimisticTxLogLinger() {
        return pessimisticTxLogLinger;
    }



    /** {@inheritDoc} */
     public int accessMode() {
        return acsMode;
    }



    /**
     * @return Whether data was zipped.
     */
    public boolean isZipped() {
        return zipped;
    }



    /**
     * @return Marker position.
     */
    public long getOffset() {
        return off;
    }



    /**
     * @return File path.
     */
    public String getPath() {
        return path;
    }



    /**
     * @return File size.
     */
    public long getSize() {
        return size;
    }



    /**
     * @return Data bytes.
     */
    public byte[] getData() {
        return data;
    }



    /**
     * @return Timestamp of last modification of the file.
     */
    public long getLastModified() {
        return lastModified;
    }



    /**
     * Tries to retrieve H2 engine error message from exception. If the exception is not of type
     * "org.h2.jdbc.JdbcSQLException" returns original error message.
     *
     * @param err Exception.
     * @return Error message.
     */
    public static String tryRetrieveH2ErrorMessage(Throwable err) {
        String msg = err.getMessage();

        Throwable e = err.getCause();
        while (e != null) {
            if (e.getClass().getCanonicalName().equals("org.h2.jdbc.JdbcSQLException")) {
                msg = e.getMessage();

                break;
            }

            e = e.getCause();
        }

        return msg;
    }



    /**
     * @return Atomic sequence reservation size.
     */
    public int getAtomicSequenceReserveSize() {
        return seqReserveSize;
    }



    /**
     * @return Number of backup nodes.
     */
    public int getBackups() {
        return backups;
    }



    /**
     * @return Cache mode.
     */
    public CacheMode getCacheMode() {
        return cacheMode;
    }



    /**
     * @return Exceptions caught during collecting IGFS from nodes.
     */
    public Map<UUID, VisorExceptionWrapper> getIgfssEx() {
        return igfssEx;
    }



    /**
     * @return Unhandled exceptions from nodes.
     */
    public Map<UUID, VisorExceptionWrapper> getUnhandledEx() {
        return unhandledEx;
    }



    /**
     * @return Exceptions caught during collecting caches from nodes.
     */
    public Map<UUID, VisorExceptionWrapper> getCachesEx() {
        return cachesEx;
    }



    /**
     * @return All caches collected from nodes.
     */
    public Map<UUID, Collection<VisorCache>> getCaches() {
        return caches;
    }



    /**
     * @return All IGFS endpoints collected from nodes.
     */
    public Map<UUID, Collection<VisorIgfsEndpoint>> getIgfsEndpoints() {
        return igfsEndpoints;
    }



    /**
     * @return Nodes error counts.
     */
    public Map<UUID, Long> getErrorCounts() {
        return errCnts;
    }



    /**
     * @return Exceptions caught during collecting events from nodes.
     */
    public Map<UUID, VisorExceptionWrapper> getEventsEx() {
        return evtsEx;
    }



    /**
     * @return All IGFS collected from nodes.
     */
    public Map<UUID, Collection<VisorIgfs>> getIgfss() {
        return igfss;
    }



    /**
     * @return All events collected from nodes.
     */
    public List<VisorGridEvent> getEvents() {
        return evts;
    }



    /**
     * @return Nodes topology versions.
     */
    public Map<UUID, Long> getTopologyVersions() {
        return topVersions;
    }



    /**
     * @return Cache name to store IGFS data.
     */
    @Nullable public String getDataCacheName() {
        return dataCacheName;
    }



    /**
     * @return IPC endpoint config to publish IGFS over.
     */
    @Nullable public String getIpcEndpointConfiguration() {
        return ipcEndpointCfg;
    }



    /**
     * @return Management port.
     */
    public int getManagementPort() {
        return mgmtPort;
    }



    /**
     * @return Map of paths to IGFS modes.
     */
    @Nullable public Map<String, IgfsMode> getPathModes() {
        return pathModes;
    }



    /**
     * @return Fragmentizer throttling delay.
     */
    public long getFragmentizerThrottlingDelay() {
        return fragmentizerThrottlingDelay;
    }



    /**
     * @return Number of batches that can be concurrently sent to remote node.
     */
    public int getPerNodeParallelBatchCount() {
        return perNodeParallelBatchCnt;
    }



    /**
     * @return IGFS instance name.
     */
    @Nullable public String getName() {
        return name;
    }



    /**
     * @return Cache name to store IGFS meta information.
     */
    @Nullable public String getMetaCacheName() {
        return metaCacheName;
    }



    /**
     * @return File's data block size.
     */
    public int getBlockSize() {
        return blockSize;
    }



    /**
     * @return Read/write buffer size for IGFS stream operations in bytes.
     */
    public int getStreamBufferSize() {
        return streamBufSize;
    }



    /**
     * @return Number of file blocks buffered on local node before sending batch to remote node.
     */
    public int getPerNodeBatchSize() {
        return perNodeBatchSize;
    }



    /**
     * @return Amount of sequential block reads before prefetch is triggered.
     */
    public int getSequenceReadsBeforePrefetch() {
        return seqReadsBeforePrefetch;
    }



    /**
     * @return Fragmentizer concurrent files.
     */
    public int getFragmentizerConcurrentFiles() {
        return fragmentizerConcurrentFiles;
    }



    /**
     * @return Fragmentizer throttling block length.
     */
    public long getFragmentizerThrottlingBlockLength() {
        return fragmentizerThrottlingBlockLen;
    }



    /**
     * @return IPC endpoint enabled flag.
     */
    public boolean isIpcEndpointEnabled() {
        return ipcEndpointEnabled;
    }



    /**
     * @return Fragmentizer enabled flag.
     */
    public boolean isFragmentizerEnabled() {
        return fragmentizerEnabled;
    }



    /**
     * @return Maximum range length.
     */
    public long getMaxTaskRangeLength() {
        return maxTaskRangeLen;
    }



    /**
     * @return Number of pre-fetched blocks if specific file's chunk is requested.
     */
    public int getPrefetchBlocks() {
        return prefetchBlocks;
    }



    /**
     * @return IGFS instance mode.
     */
    public IgfsMode getDefaultMode() {
        return dfltMode;
    }



    /**
     * Get group size.
     * <p>
     * Group size defines how many sequential file blocks will reside on the same node. This parameter
     * must correlate to Hadoop split size parameter defined in Hadoop via {@code mapred.max.split.size}
     * property. Ideally you want all blocks accessed within one split to be mapped to {@code 1} group,
     * so they can be located on the same grid node. For example, default Hadoop split size is {@code 64mb}
     * and default {@code IGFS} block size is {@code 64kb}. This means that to make sure that each split
     * goes only through blocks on the same node (without hopping between nodes over network), we have to
     * make the group size be equal to {@code 64mb / 64kb = 1024}.
     * <p>
     * Defaults to {@link #DFLT_GRP_SIZE}.
     *
     * @return Group size.
     */
    public int getGroupSize() {
        return grpSize;
    }



    /**
     * @return How many bytes were written.
     */
    public long getBytesWritten() {
        return bytesWritten;
    }



    /**
     * @return Counters for uniformity calculation.
     */
    public VisorIgfsProfilerUniformityCounters getCounters() {
        return counters;
    }



    /**
     * @return File size.
     */
    public long getSize() {
        return size;
    }



    /**
     * @return Calculated uniformity.
     */
    public double getUniformity() {
        if (uniformity < 0)
            uniformity = counters.calc();

        return uniformity;
    }



    /**
     * @return Write speed in bytes per second or {@code -1} if speed not available.
     */
    public long getWriteSpeed() {
        return writeSpeed;
    }



    /**
     * @return Path to file.
     */
    public String getPath() {
        return path;
    }



    /**
     * @return How many bytes were read.
     */
    public long getBytesRead() {
        return bytesRead;
    }



    /**
     * @return Read speed in bytes per second or {@code -1} if speed not available.
     */
    public long getReadSpeed() {
        return readSpeed;
    }



    /**
     * @return How long write take.
     */
    public long getWriteTime() {
        return writeTime;
    }



    /**
     * @return IGFS mode.
     */
    public IgfsMode getMode() {
        return mode;
    }



    /**
     * @return User write read time.
     */
    public long getUserWriteTime() {
        return userWriteTime;
    }



    /**
     * @return How long read take.
     */
    public long getReadTime() {
        return readTime;
    }



    /**
     * @return Timestamp of last file operation.
     */
    public long getTimestamp() {
        return ts;
    }



    /**
     * @return User read time.
     */
    public long getUserReadTime() {
        return userReadTime;
    }



    /**
     * @return Cache mode.
     */
    public CacheMode getMode() {
        return mode;
    }



    /**
     * @return Cache atomicity mode.
     */
    public CacheAtomicityMode getAtomicityMode() {
        return atomicityMode;
    }



    /**
     * @return Cache affinity config.
     */
    public VisorCacheAffinityConfiguration getAffinityConfiguration() {
        return affinityCfg;
    }



    /**
     * @return Class name of expiry policy factory.
     */
    public String getExpiryPolicyFactory() {
        return expiryPlcFactory;
    }



    /**
     * @return Max concurrent async operations
     */
    public int getMaxConcurrentAsyncOperations() {
        return maxConcurrentAsyncOps;
    }



    /**
     * @return Invalidate.
     */
    public boolean isInvalidate() {
        return invalidate;
    }



    /**
     * @return Eviction config.
     */
    public VisorCacheEvictionConfiguration getEvictionConfiguration() {
        return evictCfg;
    }



    /**
     * @return Write synchronization mode.
     */
    public CacheWriteSynchronizationMode getWriteSynchronizationMode() {
        return writeSynchronizationMode;
    }



    /**
     * @return Class name of cache loader factory.
     */
    public String getLoaderFactory() {
        return ldrFactory;
    }



    /**
     * @return Class name of cache writer factory.
     */
    public String getWriterFactory() {
        return writerFactory;
    }



    /**
     * @return Cache interceptor.
     */
    @Nullable public String getInterceptor() {
        return interceptor;
    }



    /**
     * @return Cache name.
     */
    @Nullable public String getName() {
        return name;
    }



    /**
     * @return {@code true} if cache statistics collection enabled.
     */
    public boolean isStatisticsEnabled() {
        return statisticsEnabled;
    }



    /**
     * @return Whether management is enabled.
     */
    public boolean isManagementEnabled() {
        return mgmtEnabled;
    }



    /**
     * @return Preload config.
     */
    public VisorCacheRebalanceConfiguration getRebalanceConfiguration() {
        return rebalanceCfg;
    }



    /**
     * @return Total remote blocks read.
     */
    public long getBlocksReadRemote() {
        return blocksRdRmt;
    }



    /**
     * @return Local free space in bytes on local node.
     */
    public long getFreeSpaceSize() {
        return totalSpaceSz - usedSpaceSz;
    }



    /**
     * @return Total bytes write.
     */
    public long getBytesWritten() {
        return bytesWrt;
    }



    /**
     * @return Total blocks read, local and remote.
     */
    public long getBlocksRead() {
        return blocksRd;
    }



    /**
     * @return Number of files stored in file system.
     */
    public int getFilesCount() {
        return filesCnt;
    }



    /**
     * @return Local used space in bytes on local node.
     */
    public long getUsedSpaceSize() {
        return usedSpaceSz;
    }



    /**
     * @return Number of files that are currently opened for reading on local node.
     */
    public int getFilesOpenedForRead() {
        return filesOpenedForRd;
    }



    /**
     * @return Total remote blocks write.
     */
    public long getBlocksWrittenRemote() {
        return blocksWrtRmt;
    }



    /**
     * @return Total bytes read time.
     */
    public long getBytesReadTime() {
        return bytesRdTm;
    }



    /**
     * @return Total bytes read.
     */
    public long getBytesRead() {
        return bytesRd;
    }



    /**
     * @return Total bytes write time.
     */
    public long getBytesWriteTime() {
        return bytesWrtTm;
    }



    /**
     * @return Maximum amount of data that can be stored on local node.
     */
    public long getTotalSpaceSize() {
        return totalSpaceSz;
    }



    /**
     * @return Number of files that are currently opened for writing on local node.
     */
    public int getFilesOpenedForWrite() {
        return filesOpenedForWrt;
    }



    /**
     * @return Total blocks write, local and remote.
     */
    public long getBlocksWritten() {
        return blocksWrt;
    }



    /**
     * @return Number of directories created in file system.
     */
    public int getFoldersCount() {
        return foldersCnt;
    }



    /**
     * @return Segmentation resolvers.
     */
    @Nullable public String getResolvers() {
        return resolvers;
    }



    /**
     * @return Segmentation policy.
     */
    public SegmentationPolicy getPolicy() {
        return plc;
    }



    /**
     * @return Whether or not node should wait for correct segment on start.
     */
    public boolean isWaitOnStart() {
        return waitOnStart;
    }



    /**
     * @return Frequency of network segment check by discovery manager.
     */
    public long getCheckFrequency() {
        return checkFreq;
    }



    /**
     * @return Cache store factory class name..
     */
    public String getStoreFactory() {
        return storeFactory;
    }



    /**
     * @return {@code true} if cache has JDBC store.
     */
    public boolean isJdbcStore() {
        return jdbcStore;
    }



    /**
     * @return Maximum batch size for write-behind cache store operations.
     */
    public int getBatchSize() {
        return batchSz;
    }



    /**
     * @return Cache store class name.
     */
    @Nullable public String getStore() {
        return store;
    }



    /**
     * @return Whether cache should operate in write-through mode.
     */
    public boolean isWriteThrough() {
        return writeThrough;
    }



    /**
     * @return Frequency with which write-behind cache is flushed to the cache store in milliseconds.
     */
    public long getFlushFrequency() {
        return flushFreq;
    }



    /**
     * @return Number of threads that will perform cache flushing.
     */
    public int getFlushThreadCount() {
        return flushThreadCnt;
    }



    /**
     * @return Maximum object count in write-behind cache.
     */
    public int getFlushSize() {
        return flushSz;
    }



    /**
     * @return Whether cache should operate in read-through mode.
     */
    public boolean isReadThrough() {
        return readThrough;
    }



    /**
     * @return Flag indicating whether write-behind behaviour should be used for the cache store.
     */
    public boolean isWriteBehindEnabled() {
        return writeBehindEnabled;
    }



    /**
     * @return Keep binary in store flag.
     */
    public boolean isStoreKeepBinary() {
        return storeKeepBinary;
    }



    /**
     * @return {@code true} if cache has store.
     */
    public boolean isEnabled() {
        return store != null;
    }



    /**
     * @return Ready topology version.
     */
    public AffinityTopologyVersion readyTopologyVersion() {
        return ctx.cache().context().exchange().readyAffinityVersion();
    }



    /**
     * Must be checked in {@link GridDhtLocalPartition#tryEvict(boolean)}.
     * If returns {@code true} this reservation object becomes invalid and partitions
     * can be evicted or at least cleared.
     * Also this means that after returning {@code true} here method {@link #reserve()} can not
     * return {@code true} anymore.
     *
     * @return {@code true} If this reservation was successfully invalidated because it was not
     *          reserved and partitions can be evicted.
     */
    public boolean invalidate() {
        assert parts.get() != null : "all parts must be reserved before registration";

        int r = reservations.get();

        assert r >= -1 : r;

        if (r != 0)
            return r == -1;

        if (reservations.compareAndSet(0, -1)) {
            unregister();

            return true;
        }

        return false;
    }



    /** {@inheritDoc} */
     public void start() throws IgniteCheckedException {
        ctx.event().addLocalEventListener(lsnr, EVT_NODE_FAILED, EVT_NODE_LEFT, EVT_NODE_JOINED);
    }



    /**
     * @return Port number.
     */
    public int getPort() {
        return port;
    }



    /**
     * @return IGFS name.
     */
    @Nullable public String getIgfsName() {
        return igfsName;
    }



    /**
     * @return Host address / name.
     */
    @Nullable public String getHostName() {
        return hostName;
    }



    /**
     * Gets read-only view of backed queue in proper order.
     *
     * @return Read-only view of backed queue.
     */
    public Collection<EvictableEntry<K, V>> queue() {
        Set<EvictableEntry<K, V>> cp = new LinkedHashSet<>();

        for (Holder<K, V> holder : set)
            cp.add(holder.entry);

        return Collections.unmodifiableCollection(cp);
    }



    /**
     * @return Number of backups.
     */
    public int getBackups() {
        return backups;
    }



    /**
     * @return Cache mode.
     */
    public CacheMode getCacheMode() {
        return cacheMode;
    }



    /**
     * @return Cache atomicity mode.
     */
    public CacheAtomicityMode getAtomicityMode() {
        return atomicityMode;
    }



    /**
     * @return Off-heap memory size.
     */
    public long getOffHeapMaxMemory() {
        return offHeapMaxMem;
    }



    /**
     * @param t Table or {@code null} to reset existing.
     */
    public void innerTable(Table t) {
        if (t == null)
            tbl.remove();
        else
            tbl.set(t);
    }



    /**
     * @return Shortened version of  result. Suitable for humans to read.
     */
    public String getShortDisplay() {
        return shortDisplay;
    }



    /**
     * @return Event type.
     */
    public int getTypeId() {
        return typeId;
    }



    /**
     * @return Event timestamp.
     */
    public long getTimestamp() {
        return ts;
    }



    /**
     * @return Globally unique ID of this event.
     */
    public IgniteUuid getId() {
        return id;
    }



    /**
     * @return Node Id where event occurred and was recorded.
     */
    public UUID getNid() {
        return nid;
    }



    /**
     * @return Name of this event.
     */
    public String getName() {
        return name;
    }



    /**
     * @return Event message.
     */
    @Nullable public String getMessage() {
        return msg;
    }



    /**
     * @return Whether IGFS has configured secondary file system.
     */
    public boolean isSecondaryFileSystemConfigured() {
        return secondaryFsConfigured;
    }



    /**
     * @return IGFS instance working mode.
     */
    public IgfsMode getMode() {
        return mode;
    }



    /**
     * @return IGFS metrics.
     */
    public VisorIgfsMetrics getMetrics() {
        return metrics;
    }



    /**
     * @return IGFS instance name.
     */
    public String getName() {
        return name;
    }



    /**
     * @return Query ID.
     */
    public long queryId() {
        return queryId;
    }



    /** {@inheritDoc} */
     protected IgniteMessaging createAsyncInstance() {
        return new IgniteMessagingImpl(ctx, prj, true);
    }



    /** {@inheritDoc} */
    @Nullable  public DiscoveryCustomMessage ackMessage() {
        return null;
    }



    /**
     * @return Number of node metrics stored in memory.
     */
    public int getHistorySize() {
        return histSize;
    }



    /**
     * @return Metrics expired time.
     */
    public long getExpireTime() {
        return expTime;
    }



    /**
     * @return Frequency of metrics log printout.
     */
    public long getLoggerFrequency() {
        return logFreq;
    }



    /**
     * @return Rows fetched from query.
     */
    public List<Object[]> getRows() {
        return rows;
    }



    /**
     * @return Whether query has more rows to fetch.
     */
    public boolean isHasMore() {
        return hasMore;
    }



    /**
     * @return Duration of next page fetching.
     */
    public long getDuration() {
        return duration;
    }



    /**
     * @return Cache eviction policy max size.
     */
    @Nullable public Integer getPolicyMaxSize() {
        return plcMaxSize;
    }



    /**
     * @return Eviction policy.
     */
    @Nullable public String getPolicy() {
        return plc;
    }



    /**
     * @return Eviction filter to specify which entries should not be evicted.
     */
    @Nullable public String getFilter() {
        return filter;
    }



    /**
     * @return Binary context.
     */
    public BinaryContext binaryContext() {
        return ctx;
    }



    static TrainingContext current()
    {
        return TRAINING_CONTEXT.get();
    }



	public <T> Page<T> continueScroll(@Nullable String scrollId, long scrollTimeInMillis, Class<T> clazz) {
		SearchResponse response = getSearchResponse(client.prepareSearchScroll(scrollId)
				.setScroll(TimeValue.timeValueMillis(scrollTimeInMillis)).execute());
		return resultsMapper.mapResults(response, clazz, Pageable.unpaged());
	}



    static StringBuilder _getSharedStringBuilder(FacesContext facesContext)
    {
        Map<Object, Object> attributes = facesContext.getAttributes();

        StringBuilder sb = (StringBuilder) attributes.get(_STRING_BUILDER_KEY);

        if (sb == null)
        {
            sb = new StringBuilder();
            attributes.put(_STRING_BUILDER_KEY, sb);
        }
        else
        {

            // clear out the stringBuilder by setting the length to 0
            sb.setLength(0);
        }

        return sb;
    }



    protected boolean compareValues(Object previous,
                                      Object value)
    {
        return previous==null?(value!=null):(!previous.equals(value));
    }



    
    public boolean isValidationFailed()
    {
        assertNotReleased();
        
        return _validationFailed;
    }



	public A getAnnotation() {
		return annotation;
	}



	public Class<A> getType() {
		return type;
	}



	public ConstraintTarget getValidationAppliesTo() {
		return getAttribute( ConstraintHelper.VALIDATION_APPLIES_TO, ConstraintTarget.class );
	}



	/**
	 * Calculate Modulo 11 checksum
	 *
	 * @param digits the digits for which to calculate the checksum
	 * @param threshold the threshold for the Mod11 algorithm multiplier growth
	 *
	 * @return the result of the mod11 checksum calculation
	 */
	public static int calculateMod11Check(final List<Integer> digits, final int threshold) {
		int sum = 0;
		int multiplier = 2;

		for ( int index = digits.size() - 1; index >= 0; index-- ) {
			sum += digits.get( index ) * multiplier++;
			if ( multiplier > threshold ) {
				multiplier = 2;
			}
		}
		return 11 - ( sum % 11 );
	}



	private boolean doValidateConstraint(ValidationContext<?> executionContext, ValueContext<?, ?> valueContext) {
		valueContext.setElementType( getElementType() );
		boolean validationResult = constraintTree.validateConstraints( executionContext, valueContext );

		return validationResult;
	}



	public static NodeImpl makeIterableAndSetMapKey(NodeImpl node, Object key) {
		return new NodeImpl(
				node.name,
				node.parent,
				true,
				null,
				key,
				node.kind,
				node.parameterTypes,
				node.parameterIndex,
				node.value,
				node.containerClass,
				node.typeArgumentIndex
		);
	}



	public static NodeImpl makeIterableAndSetIndex(NodeImpl node, Integer index) {
		return new NodeImpl(
				node.name,
				node.parent,
				true,
				index,
				null,
				node.kind,
				node.parameterTypes,
				node.parameterIndex,
				node.value,
				node.containerClass,
				node.typeArgumentIndex
		);
	}



	
	public boolean hasConstrainedReturnValue() {
		return returnValueDescriptor != null && ( returnValueDescriptor.hasConstraints()
				|| returnValueDescriptor.isCascaded() );
	}



	
	public boolean hasConstrainedParameters() {
		if ( crossParameterDescriptor.hasConstraints() ) {
			return true;
		}

		for ( ParameterDescriptor oneParameter : parameters ) {
			if ( oneParameter.hasConstraints() || oneParameter.isCascaded() ) {
				return true;
			}
		}

		return false;
	}



    
    public void setManagementContext(ManagementContext managementContext) {
        Preconditions.checkNotNull(managementContext, "management context");
        if (this.managementContext!=null && managementContext!=this.managementContext)
            throw new IllegalStateException("Cannot switch management context, from "+this.managementContext+" to "+managementContext);
        this.managementContext = managementContext;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Predicate<RegisteredType> subtypeOf(final Class<?> filter) {
        // the assignableFrom predicate checks if this class is assignable from the subsequent *input*.
        // in other words, we're checking if any input is a subtype of this class
        return anySuperType((Predicate)Predicates.assignableFrom(filter));
    }



    
    public void setManagementContext(ManagementContext managementContext) {
        this.managementContext = managementContext;
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    
    public void setManagementContext(ManagementContext managementContext) {
        if (this.managementContext!=null) {
            if (this.managementContext.equals(managementContext)) return;
            throw new IllegalStateException("ManagementContext cannot be changed: specified twice for Brooklyn Jersey Resource "+this);
        }
        this.managementContext = managementContext;
    }



    private static boolean testForMicrosoftWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        //see org.apache.commons.lang.SystemUtils.IS_WINDOWS
        return os.startsWith("windows");
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    
    public void setManagementContext(ManagementContext mgmt) {
        this.mgmt = mgmt;
    }



    public long position()
    {
        return getLength();
    }



    private void maybeGossipToUnreachableMember(MessageOut<GossipDigestSyn> message)
    {
        double liveEndpointCount = liveEndpoints.size();
        double unreachableEndpointCount = unreachableEndpoints.size();
        if (unreachableEndpointCount > 0)
        {
            /* based on some probability */
            double prob = unreachableEndpointCount / (liveEndpointCount + 1);
            double randDbl = random.nextDouble();
            if (randDbl < prob)
                sendGossip(message, unreachableEndpoints.keySet());
        }
    }



    public TabularData getCompactionHistory()
    {
        return compactionProxy.getCompactionHistory();
    }



    public long unsharedHeapSizeExcludingData()
    {
        return EMPTY_SIZE
             + ObjectSizes.sizeOnHeapExcludingData(bytes)
             + ObjectSizes.sizeOf(text);
    }



    List<ByteBuffer> rowArgs(Row row)
    {
        List<ByteBuffer> args = new ArrayList<>();
        for (int i : argumentIndex)
            args.add(spec.partitionGenerator.convert(i,
                        row.get(i)));
        return args;
    }



    public Unfiltered next()
    {
        try
        {
            return doCompute();
        }
        catch (IndexOutOfBoundsException e)
        {
            sstable.markSuspect();
            throw new CorruptSSTableException(e, filename);
        }
        catch (IOError e)
        {
            if (e.getCause() instanceof IOException)
            {
                sstable.markSuspect();
                throw new CorruptSSTableException((Exception)e.getCause(), filename);
            }
            else
            {
                throw e;
            }
        }
    }



    public long position()
    {
        return current();
    }



    public AbstractCompactionTask setUserDefined(boolean isUserDefined)
    {
        this.isUserDefined = isUserDefined;
        return this;
    }



    public void setCapacity(long capacity)
    {
        map.setCapacity(capacity);
    }



    UUID id()
    {
        return id;
    }



    public static boolean isIncrementalBackupsEnabled()
    {
        return conf.incremental_backups;
    }



    public boolean shouldCheckCrc()
    {
        double checkChance = getCrcCheckChance();
        return checkChance > 0d && checkChance > ThreadLocalRandom.current().nextDouble();
    }



  /**
   * clears a bit.
   * The index should be less than the OpenBitSet size.
   */
  public void clear(int index) {
    int wordNum = index >> 6;
    int bit = index & 0x03f;
    long bitmask = 1L << bit;
    bits[wordNum / PAGE_SIZE][wordNum % PAGE_SIZE] &= ~bitmask;
    // hmmm, it takes one more instruction to clear than it does to set... any
    // way to work around this?  If there were only 63 bits per word, we could
    // use a right shift of 10111111...111 in binary to position the 0 in the
    // correct place (using sign extension).
    // Could also use Long.rotateRight() or rotateLeft() *if* they were converted
    // by the JVM into a native instruction.
    // bits[word] &= Long.rotateLeft(0xfffffffe,bit);
  }



    public static LivenessInfo withExpirationTime(long timestamp, int ttl, int localExpirationTime)
    {
        return ttl == NO_TTL ? new LivenessInfo(timestamp) : new ExpiringLivenessInfo(timestamp, ttl, localExpirationTime);
    }



    /**
     * The total number of columns in this object.
     *
     * @return the total number of columns in this object.
     */
    public int size()
    {
        return BTree.size(columns);
    }



    private void finish() throws IOException
    {
        UnfilteredSerializer.serializer.writeEndOfPartition(writer);

        // It's possible we add no rows, just a top level deletion
        if (written == 0)
            return;

        // the last column may have fallen on an index boundary already.  if not, index it explicitly.
        if (firstClustering != null)
            addIndexBlock();

        // If we serialize the IndexInfo objects directly in the code above into 'buffer',
        // we have to write the offsts to these here. The offsets have already been are collected
        // in indexOffsets[]. buffer is != null, if it exceeds Config.column_index_cache_size_in_kb.
        // In the other case, when buffer==null, the offsets are serialized in RowIndexEntry.IndexedEntry.serialize().
        if (buffer != null)
            RowIndexEntry.Serializer.serializeOffsets(buffer, indexOffsets, columnIndexCount);

        // we should always have at least one computed index block, but we only write it out if there is more than that.
        assert columnIndexCount > 0 && headerLength >= 0;
    }



    public Void deserialize(ByteBuffer bytes)
    {
        return null;
    }



    public ByteBuffer serialize(Void value)
    {
        return ByteBufferUtil.EMPTY_BYTE_BUFFER;
    }



    public ByteBuffer serialize(Float value)
    {
        return (value == null) ? ByteBufferUtil.EMPTY_BYTE_BUFFER : ByteBufferUtil.bytes(value);
    }



    public String deserialize(ByteBuffer bytes)
    {
        try
        {
            return ByteBufferUtil.string(bytes, charset);
        }
        catch (CharacterCodingException e)
        {
            throw new MarshalException("Invalid " + charset + " bytes " + ByteBufferUtil.bytesToHex(bytes));
        }
    }



    public ByteBuffer serialize(Double value)
    {
        return (value == null) ? ByteBufferUtil.EMPTY_BYTE_BUFFER : ByteBufferUtil.bytes(value);
    }



    public ByteBuffer serialize(Integer value)
    {
        return value == null ? ByteBufferUtil.EMPTY_BYTE_BUFFER : ByteBufferUtil.bytes(value);
    }



    /**
     * Returns the current position of the underlying target like a file-pointer
     * or the position withing a buffer. Not every implementation may support this
     * functionality. Whether or not this functionality is supported can be checked
     * via the {@link #hasPosition()}.
     *
     * @throws UnsupportedOperationException if the implementation does not support
     *                                       position
     */
    default long position()
    {
        throw new UnsupportedOperationException();
    }



    public String getText()
    {
        return "(" + type + ")" + term;
    }



    public DecoratedKey partitionKey()
    {
        return key;
    }



    public ByteBuffer serialize(Boolean value)
    {
        return (value == null) ? ByteBufferUtil.EMPTY_BYTE_BUFFER
                : value ? TRUE : FALSE; // false
    }



    public void clearCache() { cache.clear(); }



    /**
     * Invoked when the Registry is shutdown; sets the shutdown flag and releases the object and the creator.
     */
    public void run()
    {
        creator = new ObjectCreator<T>()
        {
            public T createObject()
            {
                throw new IllegalStateException(ServiceMessages.registryShutdown(serviceId));
            }
        };

        object = null;
    }



    List<T> toMutableList()
    {
        List<T> result = new ArrayList<T>(count);

        for (int i = 0; i < count; i++)
        {
            result.add(values[start + i]);
        }

        return result;
    }



    public void run()
    {
        registry.clearCache();
        cache.clear();
    }



    /**
     * @return test parameter (if negate is false), or test parameter inverted (if negate is true)
     */
    protected boolean test()
    {
        return test != negate;
    }



    public void run()
    {
        changeTracker.clear();
        classToInstantiator.clear();
        proxyFactory.clearCache();

        // Release the existing class pool, loader and so forth.
        // Create a new one.

        initializeService();
    }



    /**
     * Clears the registry on an invalidation event (this is because the registry caches results, and the keys are
     * classes that may be component classes from the invalidated component class loader).
     */
    public void run()
    {
        registry.clearCache();
    }



    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    @Contribute(RequestHandler.class)
    public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration,
     @Local
     RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }



    Object doUpdateSelected(String nodeId, boolean selected)
    {
        TreeNode node = model.getById(nodeId);

        String event;

        if (selected)
        {
            selectionModel.select(node);

            event = EventConstants.NODE_SELECTED;
        } else
        {
            selectionModel.unselect(node);

            event = EventConstants.NODE_UNSELECTED;
        }

        CaptureResultCallback<Object> callback = CaptureResultCallback.create();

        resources.triggerEvent(event, new Object[]{nodeId}, callback);

        final Object result = callback.getResult();

        if (result != null)
        {
            return result;
        }

        return new JSONObject();
    }



    Object doExpandChildren(String nodeId)
    {
        TreeNode container = model.getById(nodeId);

        expansionModel.markExpanded(container);

        return new RenderNodes(container.getChildren());
    }



    Object doMarkCollapsed(@RequestParameter(NODE_ID) String nodeId)
    {
        expansionModel.markCollapsed(model.getById(nodeId));

        return new JSONObject();
    }



    Object doMarkExpanded(@RequestParameter(NODE_ID) String nodeId)
    {
        expansionModel.markExpanded(model.getById(nodeId));

        return new JSONObject();
    }



    /**
     * Creates an infinite lazy flow from an initial value and a function to map from the current value to the
     * next value.
     * 
     * @param <T>
     * @param initial
     *            initial value in flow
     * @param function
     *            maps from current value in flow to next value in flow
     * @return lazy flow
     */
    public static <T> Flow<T> iterate(final T initial, final Mapper<T, T> function)
    {
        LazyFunction<T> head = new LazyFunction<T>()
        {
            public LazyContinuation<T> next()
            {
                return new LazyContinuation<T>(initial, toLazyFunction(initial, function));
            }
        };

        return lazy(head);
    }



    public <T> void addLoggingAdvice(Logger logger, MethodAdviceReceiver receiver)
    {
        MethodAdvice advice = new LoggingAdvice(logger, exceptionTracker);

        receiver.adviseAllMethods(advice);
    }



    private <T> T getServiceByTypeAlone(Class<T> serviceInterface)
    {
        List<String> serviceIds = findServiceIdsForInterface(serviceInterface);

        if (serviceIds == null)
            serviceIds = Collections.emptyList();

        switch (serviceIds.size())
        {
            case 0:

                throw new RuntimeException(IOCMessages.noServiceMatchesType(serviceInterface));

            case 1:

                String serviceId = serviceIds.get(0);

                return getService(serviceId, serviceInterface);

            default:

                Collections.sort(serviceIds);

                throw new RuntimeException(IOCMessages.manyServiceMatches(serviceInterface, serviceIds));
        }
    }



    /**
     * Invoked by InvalidationEventHub
     */
    
    public void run()
    {
        registry.clearCache();
    }



    public Method getMethod()
    {
        return bundle.getMethod(getInstance());
    }



    /**
     * @return test parameter inverted
     */
    protected boolean test()
    {
        return !test;
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> storeTraversal) {
        this.storeTraversal = this.integrateChild(storeTraversal);
    }



    
    public <U> Iterator<Property<U>> properties(String... propertyKeys) {
        return Collections.emptyIterator();
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> treeTraversal) {
        this.traversalRing.addTraversal(this.integrateChild(treeTraversal));
    }



    
    public Iterator<Vertex> vertices(final Object... vertexIds) {
        return Collections.emptyIterator();
    }



    
    public Iterator<Vertex> vertices(final Direction direction) {
        switch (direction) {
            case OUT:
                return IteratorUtils.of(this.outVertex);
            case IN:
                return IteratorUtils.of(this.inVertex);
            default:
                return IteratorUtils.of(this.outVertex, this.inVertex);
        }
    }



    /**
     * Called from the {@link #configure(Map, Map)} method right before the call to create the builder. Sub-classes
     * can choose to alter the builder or completely replace it.
     */
    public GryoMapper.Builder configureBuilder(final GryoMapper.Builder builder, final Map<String, Object> config,
                                               final Map<String, Graph> graphs) {
        return builder;
    }



    public static Service instance() {
        return INSTANCE;
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> selectTraversal) {
        this.traversalRing.addTraversal(this.integrateChild(selectTraversal));
    }



    public final void putTraversalSource(final String tsName, final TraversalSource ts) {
        traversalSources.put(tsName, ts);
    }



    /**
     * Renames a key in the parameter set.
     *
     * @param oldKey the key to rename
     * @param newKey the new name of the key
     */
    public void rename(final Object oldKey, final Object newKey) {
        this.parameters.put(newKey, this.parameters.remove(oldKey));
    }



    public String getPoolInfo() {
        final StringBuilder sb = new StringBuilder("ConnectionPool (");
        sb.append(host);
        sb.append(") - ");
        connections.forEach(c -> {
            sb.append(c);
            sb.append(",");
        });
        return sb.toString().trim();
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> selectTraversal) {
        this.selectTraversal = this.integrateChild(selectTraversal);
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> pathTraversal) {
        this.traversalRing.addTraversal(this.integrateChild(pathTraversal));
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> treeTraversal) {
        this.traversalRing.addTraversal(this.integrateChild(treeTraversal));
    }



    public static boolean hasAllStepsOfClass(final Traversal.Admin<?, ?> traversal, final Class<?>... classesToCheck) {
        for (final Step step : traversal.getSteps()) {
            boolean foundInstance = false;
            for (final Class<?> classToCheck : classesToCheck) {
                if (classToCheck.isInstance(step)) {
                    foundInstance = true;
                    break;
                }
            }
            if (!foundInstance)
                return false;
        }
        return true;
    }



    public static GremlinScriptEngineManager instance(){
        return cached;
    }



    
    public GryoMapper.Builder configureBuilder(final GryoMapper.Builder builder, final Map<String, Object> config,
                                               final Map<String, Graph> graphs) {
        return overrideWithLite(builder);
    }



    public String getConnectionInfo() {
        return String.format("Connection{host=%s, isDead=%s, inFlight=%s, pending=%s}",
                pool.host, isDead, inFlight, pending.size());
    }



    
    public void modulateBy(final Traversal.Admin<?, ?> sackTraversal) {
        this.sackTraversal = this.integrateChild(sackTraversal);
    }



  public Map<String, Object> asMap() {
    return _valueMap;
  }



  private BinaryDocValues newBinary(FieldInfo field) throws IOException {
    BinaryEntry bytes = binaries.get(field.number);
    if (bytes.minLength == bytes.maxLength) {
      return getFixedBinary(field, bytes);
    } else {
      return getVariableBinary(field, bytes);
    }
  }



  LongNumericDocValues newNumeric(NumericEntry entry) throws IOException {
    final IndexInput data = this.data.clone();
    data.seek(entry.offset);

    final BlockPackedReader reader = new BlockPackedReader(data, entry.packedIntsVersion, entry.blockSize, entry.count,
        true);
    return new LongNumericDocValues() {
      
      public long get(long id) {
        return reader.get(id);
      }
    };
  }



  public List<? extends Object> asList() {
    return _values;
  }



  private Lock findInternalLock() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
      IllegalAccessException {
    Field field = org.apache.lucene.index.IndexWriter.class.getDeclaredField("writeLock");
    field.setAccessible(true);
    return (Lock) field.get(this);
  }



  /**
   * Invoke the event handler and pass a reference to self as a parameter.
   */
  
  public final void run() {
    this.handler.onNext(this);
  }



    /**
     * ArtifactOrigin instance used when the origin is unknown.
     */
    public static final ArtifactOrigin unknown(Artifact artifact) {
        return new ArtifactOrigin(artifact, false, UNKNOWN);
    }



    protected void logCircularDependency(ModuleRevisionId[] mrids) {
        Message.warn("circular dependency found: " 
            + CircularDependencyHelper.formatMessage(mrids));
    }



    private boolean hasCredentialsConfigured(final URL url) {
        return CredentialsStore.INSTANCE.hasCredentials(url.getHost());
    }



    protected void logCircularDependency(ModuleRevisionId[] mrids) {
        Message.verbose("circular dependency found: " 
            + CircularDependencyHelper.formatMessage(mrids));
    }



    private void ensureAuthorityCodes() {
        if (authorityCodes == null) {
            authorityCodes = Lists.newArrayList();
        }
    }



	/**
	 * Override this method if you want a customized {@link CouchbaseEnvironment}.
	 * This environment will be managed by Spring, which will call its shutdown()
	 * method upon bean destruction, unless you override {@link #isEnvironmentManagedBySpring()}
	 * as well to return false.
	 *
	 * @return a customized environment, defaults to a {@link DefaultCouchbaseEnvironment}.
	 */
	protected CouchbaseEnvironment getEnvironment() {
		return DefaultCouchbaseEnvironment.create();
	}



    /**
     * Returns the direct parents of this feature type.
     *
     * <div class="warning"><b>Warning:</b>
     * The type of list elements will be changed to {@code FeatureType} if and when such interface
     * will be defined in GeoAPI.</div>
     *
     * @return The parents of this feature type, or an empty set if none.
     */
    public Set<DefaultFeatureType> getSuperTypes() {
        return superTypes;
    }



    /**
     * Converts the given path to the target type of this converter.
     * This method verifies that the given path is non-null,
     * then delegates to {@link #doConvert(S)}.
     *
     * @param  source The path to convert, or {@code null}.
     * @return The converted value, or {@code null} if the given path was null.
     * @throws UnconvertibleObjectException If an error occurred during the conversion.
     */
    
    public final T apply(final S source) throws UnconvertibleObjectException {
        if (source == null) {
            return null;
        }
        try {
            return doConvert(source);
        } catch (Exception e) {
            throw new UnconvertibleObjectException(formatErrorMessage(source), e);
        }
    }



    /** Converts the given angle. */
     public Double apply(final Angle object) {
        return object.degrees();
    }



    /**
     * Declares that a word in the SQL script needs to be replaced by the given word.
     * The replacement is performed only for occurrences outside identifiers or texts.
     *
     * <div class="note"><b>Example</b>
     * this is used for mapping the table names in the EPSG scripts to table names as they were in the MS-Access
     * flavor of EPSG database. It may also contains the mapping between SQL keywords used in the SQL scripts to
     * SQL keywords understood by the database (for example Derby does not support the {@code TEXT} data type,
     * which need to be replaced by {@code VARCHAR}).</div>
     *
     * If a text to replace contains two or more words, then this map needs to contain an entry for the first word
     * associated to the {@link #MORE_WORDS} value. For example if one needs to replace the {@code "CREATE TABLE"}
     * words, then in addition to the {@code "CREATE TABLE"} entry this {@code replacements} map shall also contain
     * a {@code "CREATE"} entry associated with the {@link #MORE_WORDS} value.
     *
     * @param inScript The word in the script which need to be replaced.
     * @param replacement The word to use instead.
     */
    protected final void addReplacement(final String inScript, final String replacement) {
        if (replacements.put(inScript, replacement) != null) {
            throw new IllegalArgumentException(inScript);
        }
    }



    /**
     * Workaround for the lack of {@code LocationType} interface in GeoAPI 3.0.
     * This workaround will be removed in a future SIS version if the location
     * type interface is introduced in a future GeoAPI version.
     */
    final AbstractLocationType type() {
        return type;
    }



    /**
     * Writes the given color if {@code colorEnabled} is {@code true}.
     */
    private static void writeColor(final Appendable out, final X364 color, final boolean colorEnabled)
            throws IOException
    {
        if (colorEnabled) {
            out.append(color.sequence());
        }
    }



    /**
     * Returns {@code true} if the given NetCDF attribute is either null or equals to the
     * string value of the given metadata value.
     *
     * @param metadata  The value stored in the metadata object.
     * @param attribute The value parsed from the NetCDF file.
     */
    private static boolean canShare(final CharSequence metadata, final String attribute) {
        return (attribute == null) || (metadata != null && metadata.toString().equals(attribute));
    }



    /**
     * Returns {@code true} if the given NetCDF attribute is either null or equals to one
     * of the values in the given collection.
     *
     * @param metadata  The value stored in the metadata object.
     * @param attribute The value parsed from the NetCDF file.
     */
    private static boolean canShare(final Collection<String> metadata, final String attribute) {
        return (attribute == null) || metadata.contains(attribute);
    }



    /**
     * Returns the specified {@link Range} as a {@code NumberRange} object. If the specified
     * range is already an instance of {@code NumberRange}, then it is returned unchanged.
     * Otherwise a new number range is created using the {@linkplain #NumberRange(Range)
     * copy constructor}.
     *
     * @param  <N> The type of elements in the given range.
     * @param  range The range to cast or copy.
     * @return The same range than {@code range} as a {@code NumberRange} object.
     */
    public static <N extends Number & Comparable<? super N>> NumberRange<N> castOrCopy(final Range<N> range) {
        if (range instanceof NumberRange<?>) {
            return (NumberRange<N>) range;
        }
        // The constructor will ensure that the range element type is a subclass of Number.
        return new NumberRange<>(range);
    }



    /**
     * Returns resources in the given locale.
     *
     * @param  locale  the locale, or {@code null} for the default locale.
     * @return resources in the given locale.
     * @throws MissingResourceException if resources can not be found.
     */
    public static Resources forLocale(final Locale locale) throws MissingResourceException {
        return getBundle(Resources.class, locale);
    }



    /**
     * Returns the given object unchanged.
     *
     * @param source The value to convert.
     */
    
    public T apply(final S source) {
        return source;
    }



    /**
     * Adds the given byte in the {@link #buffer}, increasing its capacity if needed.
     */
    private void remember(final int c) {
        if (length == buffer.length) {
            buffer = Arrays.copyOf(buffer, length*2);
        }
        buffer[length++] = (byte) c;
    }



    /**
     * Creates a new descriptor with the same properties than the {@code provided} one, but completed with
     * information not found in GML. Those extra information are given by the {@code complete} descriptor.
     *
     * <p>It is the caller's responsibility to construct the {@code merged} properties as a merge of the properties
     * of the two given descriptors. This can be done with the help of {@link #mergeArrays(String, Class, Collection,
     * Map, Identifier)} among others.</p>
     */
    private static <T> ParameterDescriptor<T> create(final Map<String,?>          merged,
                                                     final ParameterDescriptor<?> provided,
                                                     final ParameterDescriptor<T> complete)
    {
        final Class<T> valueClass = complete.getValueClass();
        return new DefaultParameterDescriptor<>(merged,
                provided.getMinimumOccurs(),
                provided.getMaximumOccurs(),
                // Values below this point are not provided in GML documents,
                // so they must be inferred from the pre-defined descriptor.
                valueClass,
                Parameters.getValueDomain(complete),
                CollectionsExt.toArray(complete.getValidValues(), valueClass),
                complete.getDefaultValue());
    }



    /**
     * Adds an {@code IdentifiedObject} identifier fully specified by the given identifier.
     * This method ignores the authority, {@link #setCodeSpace(Citation, String) code space} or
     * {@link #setVersion(String) version} specified to this builder (if any), since the given
     * identifier already contains those information.
     *
     * <p><b>Lifetime:</b>
     * all identifiers are cleared after a {@code createXXX(???)} method has been invoked.</p>
     *
     * @param  identifier The {@code IdentifiedObject} identifier.
     * @return {@code this}, for method call chaining.
     */
    public B addIdentifier(final ReferenceIdentifier identifier) {
        ensureNonNull("identifier", identifier);
        identifiers.add(identifier);
        return self();
    }



    /**
     * Sets the {@code ReferenceIdentifier} version of object definitions. This method is typically invoked only once,
     * since a compound object often uses the same version for all individual components.
     *
     * <p><b>Condition:</b>
     * this method can not be invoked after one or more names or identifiers have been added (by calls to the
     * {@code addName(???)} or {@code addIdentifier(???)} methods) for the next object to create. This method can be
     * invoked again after the name, aliases and identifiers have been cleared by a call to {@code createXXX(???)}.</p>
     *
     * <p><b>Lifetime:</b>
     * this property is kept unchanged until this {@code setVersion(???)} method is invoked again.</p>
     *
     * @param  version The version of code definitions, or {@code null} if none.
     * @return {@code this}, for method call chaining.
     * @throws IllegalStateException if {@code addName(???)} or {@code addIdentifier(???)} has been invoked at least
     *         once since builder construction or since the last call to a {@code createXXX(???)} method.
     */
    public B setVersion(final String version) {
        setProperty(ReferenceIdentifier.VERSION_KEY, version);
        return self();
    }



    /**
     * To be overridden by {@link MeasurementRange} only.
     */
    Unit<?> unit() {
        return null;
    }



    /**
     * {@inheritDoc}
     */
    
    public void transpose() {
        double swap;
        swap = m01; m01 = m10; m10 = swap;
        swap = m02; m02 = m20; m20 = swap;
        swap = m12; m12 = m21; m21 = swap;
    }



    /**
     * The proxy to use for a given type declared in a URN.
     * For example in the {@code "urn:ogc:def:crs:EPSG::4326"} URN, the proxy to use is {@link #CRS}.
     *
     * @param  typeName The URN type.
     * @return The proxy for the given type, or {@code null} if the given type is illegal.
     */
    @SuppressWarnings("unchecked")
    final AuthorityFactoryProxy<? extends T> specialize(final String typeName) {
        final AuthorityFactoryProxy<?> c = BY_URN_TYPE.get(typeName.toLowerCase(Locale.US));
        if (c != null) {
            if (c.type.isAssignableFrom(type)) {
                return this;
            }
            if (type.isAssignableFrom(c.type)) {
                return (AuthorityFactoryProxy<? extends T>) c;
            }
        }
        return null;
    }



    /**
     * Converts the given string to the target type of this converter.
     * This method verifies that the given string is non-null and non-empty,
     * then delegates to {@link #doConvert(String)}.
     *
     * @param  source The string to convert, or {@code null}.
     * @return The converted value, or {@code null} if the given string was null or empty.
     * @throws UnconvertibleObjectException If an error occurred during the conversion.
     */
    
    public final T apply(String source) throws UnconvertibleObjectException {
        source = CharSequences.trimWhitespaces(source);
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            return doConvert(source);
        } catch (UnconvertibleObjectException e) {
            throw e;
        } catch (Exception e) {
            throw new UnconvertibleObjectException(formatErrorMessage(source), e);
        }
    }



    /**
     * Returns resources in the given locale.
     *
     * @param  locale  the locale, or {@code null} for the default locale.
     * @return resources in the given locale.
     * @throws MissingResourceException if resources can't be found.
     */
    public static Resources forLocale(final Locale locale) throws MissingResourceException {
        return getBundle(Resources.class, locale);
    }



    /**
     * Invoked when {@code Merger}??can not merge a metadata value by itself.
     * The default implementation throws an {@link InvalidMetadataException}.
     * Subclasses can override this method if they want to perform a different processing.
     *
     * @param target        the metadata instance in which the value should have been written.
     * @param propertyName  the name of the property to write.
     * @param sourceValue   the value to write.
     * @param targetValue   the value that already exist in the target metadata.
     */
    protected void merge(ModifiableMetadata target, String propertyName, Object sourceValue, Object targetValue) {
        throw new InvalidMetadataException(errors().getString(Errors.Keys.ValueAlreadyDefined_1, name(target, propertyName)));
    }



    /**
     * Returns the current tensor size for each dimensions.
     */
    private int[] size() {
        final int[] indices = new int[dimensions.length];
        for (int i=0; i<indices.length; i++) {
            indices[i] = dimensions[i].intValue();
        }
        return indices;
    }



    /**
     * Verifies that {@code B} in {@code <B extends Builder<B>} is the expected class.
     * This method is for assertion purposes only.
     */
    private static boolean verifyParameterizedType(final Class<?> expected) {
        for (Class<?> c = expected; c != null; c = c.getSuperclass()) {
            Type type = c.getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                final ParameterizedType p = (ParameterizedType) type;
                if (p.getRawType() == Builder.class) {
                    type = p.getActualTypeArguments()[0];
                    if (type == expected) return true;
                    throw new AssertionError(type);
                }
            }
        }
        return false;
    }



    /**
     * Converts the given number to a string.
     */
    
    public String apply(final S source) {
        return (source != null) ? source.toString() : null;
    }



    /**
     * Returns the offset applied after this logarithmic function.
     */
    double offset() {
        return 0;
    }



    /**
     * Returns the natural logarithm of the base of this logarithmic function.
     * More specifically, returns <code>{@linkplain Math#log(double) Math.log}({@link #base()})</code>.
     */
    double lnBase() {
        return 1;
    }



    /**
     * Returns resources in the given locale.
     *
     * @param  locale  the locale, or {@code null} for the default locale.
     * @return resources in the given locale.
     * @throws MissingResourceException if resources can not be found.
     */
    public static Resources forLocale(final Locale locale) throws MissingResourceException {
        return getBundle(Resources.class, locale);
    }



    /**
     * Returns the given collection as a {@code CheckedArrayList} instance of the given element type.
     *
     * @param  <E>        The element type.
     * @param  collection The collection or {@code null}.
     * @param  type       The element type.
     * @return The given collection as a {@code CheckedArrayList}, or {@code null} if the given collection was null.
     * @throws ClassCastException if an element is not of the expected type.
     *
     * @since 0.5
     */
    @SuppressWarnings("unchecked")
    public static <E> CheckedArrayList<E> castOrCopy(final Collection<?> collection, final Class<E> type) {
        if (collection == null) {
            return null;
        }
        if (collection instanceof CheckedArrayList<?> && ((CheckedArrayList<?>) collection).type == type) {
            return (CheckedArrayList<E>) collection;
        } else {
            final CheckedArrayList<E> list = new CheckedArrayList<>(type, collection.size());
            list.addAll((Collection) collection); // addAll will perform the type checks.
            return list;
        }
    }



    /**
     * Returns the error message for the exception to throw if the EPSG tables are not found and we can not create them.
     */
    static String tableNotFound(final Locale locale) {
        return Errors.getResources(locale).getString(Errors.Keys.TableNotFound_1, SENTINEL[MIXED_CASE]);
    }



    /**
     * Returns resources in the given locale.
     *
     * @param  locale  the locale, or {@code null} for the default locale.
     * @return resources in the given locale.
     * @throws MissingResourceException if resources can not be found.
     */
    public static Resources forLocale(final Locale locale) throws MissingResourceException {
        return getBundle(Resources.class, locale);
    }



    /**
     * {@inheritDoc}
     */
    
    public void transpose() {
        final double swap = m10;
        m10 = m01;
        m01 = swap;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    public java.io.Serializable getKey() {
        if (this.isEncodeURLNeeded) {
            return null;
        } else {
            return "1";
        }
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        if (this.isEncodeURLNeeded) {
            return null;
        } else {
            return NOPValidity.SHARED_INSTANCE;
        }
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        if (this.inputSource.getLastModified() != 0) {
            AggregatedValidity validity = new AggregatedValidity();
            validity.add(new TimeStampValidity(page));
            validity.add(this.inputSource.getValidity());
            return validity;
        } else {
            return null;
        }
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     * This method must be invoked before the generateValidity() method.
     *
     * @return The generated key or <code>null</code> if the component
     *              is currently not cacheable.
     */
    public Serializable getKey() {
        if (this.inputSource.getLastModified() != 0) {
            return this.inputSource.getURI() + page;
        } else {
            return null;
        }
    }



    /**
     * The component isn't cached (yet)
     */
    public java.io.Serializable getKey() {
        return null;
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    public void recycle () {
        this.contentHandler = null;
        this.lexicalHandler = null;
    }



    /**
     * Disable caching
     */
    public java.io.Serializable getKey() {
        return null;
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return null;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     * This method must be invoked before the generateValidity() method.
     *
     * @return The generated key or <code>null</code> if the component
     *              is currently not cacheable.
     */
    public Serializable getKey() {
        return null;
    }



    public void destroy() {
        doStop();
    }



    /**
     * Tests if any of the elements in a List is not null.
     * @param list
     * @return
     */
    private boolean hasNonNullElements(List list) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            if (iter.next() != null) {
                return true;
            }
        }
        return false;
    }



    /** Reset all internal state.
     * This method is called by the component manager before this
     * component is return to its pool.
     */
    public void recycle() {
        file = null;
        srcDir = null;
        destDir = null;
        classpath = null;
        encoding = null;
        errors = null;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    public Serializable getKey() {
        return this.grammarSource.getURI();
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return this.grammarSource.getValidity();
    }



    /**
     * @see org.apache.cocoon.caching.CacheableProcessingComponent#getValidity()
     */
    public SourceValidity getValidity() {
        if (this.supportCaching 
            && null != this.cacheManager
            && this.cachingSession.getExpires() > 0
            && !this.cachingSession.isPurging()) {
            return this.cachingSession.getExpiresValidity();
        }
        return null;
    }



    /**
     * @see org.apache.cocoon.caching.CacheableProcessingComponent#getKey()
     */
    public Serializable getKey() {
        if (this.supportCaching 
            && null != this.cacheManager 
            && this.cachingSession.getExpires() > 0) {
            return "1";
        }
        return null;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key
     */
    public Serializable getKey() {
        return "1";
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    public boolean isSelectable(Object obj) {
        return Web3DataSourceSelectorImpl.pools.containsKey(obj);
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        /*
        * VG: Key is generated using parameter/value pairs,
        * so this information does not need to be verified again
        * (if parameter added/removed or value changed, key should
        * change also), only stylesheet's validity is included.
        */
        return this.transformerValidity;
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    public java.io.Serializable getKey() {
        return this.source;
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        if (this.lastModified > 0) {
            return new TimeStampValidity(this.lastModified);
        } else {
            if (this.defaultCache) {
                return NOPValidity.SHARED_INSTANCE;
            } else {
                return null;
            }
        }
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return this.inputSource.getValidity();
    }



    /**
     * Recycle the component
     */
    public void recycle() {
        this.out = null;
        this.resolver = null;
        this.source = null;
        this.parameters = null;
        this.objectModel = null;
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    /**
     * recylcle this object, relasing resources
     */
    public void recycle() {
        crawled = null;
        urlsToProcess = null;
        urlsNextDepth = null;
        depth = -1;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     * This method must be invoked before the generateValidity() method.
     *
     * @return The generated key or <code>0</code> if the component
     *              is currently not cacheable.
     */
    public Serializable getKey() {
        return "1";
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    /**
     * The component isn't cached (yet)
     */
    public java.io.Serializable getKey() {
        return null;
    }



    /**
     * Recycle this component: clear logic sheet list and dependencies.
     */
    public void recycle() {
        this.logicSheetList.clear();
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object, <code>NOPCacheValidity</code>
     *         is the default if hasContentChange() gives false otherwise
     *         <code>null</code> will be returned.
     */
    public SourceValidity getValidity() {
        if (hasContentChanged(request))
            return null;
        else
            return NOPValidity.SHARED_INSTANCE;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     * This method must be invoked before the generateValidity() method.
     *
     * @return The generated key or <code>null</code> if the component
     *         is currently not cacheable.
     */
    public Serializable getKey() {
        return null;
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     * This method must be invoked before the generateValidity() method.
     *
     * @return The generated key or <code>0</code> if the component
     *              is currently not cacheable.
     */
    public java.io.Serializable getKey() {
        return this.elementName + '<' + this.count + '>' + this.blocknr;
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    public SourceValidity getValidity() {
        return NOPValidity.SHARED_INSTANCE;
    }



    public java.io.Serializable getKey() {
        return "1";
    }



    private void doStop() {
        this.doRun = false;
    }



    private void doStart() throws Exception {
        this.doRun = true;
        Thread checker = new Thread(this);
        if (getLogger().isDebugEnabled()) {
            getLogger().debug("Intializing checker thread");
        }
        checker.setPriority(getPriority());
        checker.setDaemon(true);
        checker.setName("checker");
        checker.start();
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return this.inputSource.getValidity();
    }



    /**
     * Generate the validity object.
     *
     *@return    The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return this.inputSource.getValidity();
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     *@return    The generated key hashes the src
     */
    public java.io.Serializable getKey() {
        return this.inputSource.getURI();
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    public Serializable getKey() {
        return this.lexiconSource.getURI();
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return this.lexiconSource.getValidity();
    }



    /**
     * Generate the validity object.
     * Before this method can be invoked the generateKey() method
     * must be invoked.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        if (this.inputSource == null)
            return null;
        return this.inputSource.getValidity();
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     * This method must be invoked before the generateValidity() method.
     *
     * @return The generated key or <code>0</code> if the component
     *              is currently not cacheable.
     */
    public java.io.Serializable getKey() {
        if (this.inputSource == null)
            return null;
            
        if (this.xpath != null) {
            StringBuffer buffer = new StringBuffer(this.inputSource.getURI());
            buffer.append(':').append(this.xpath);
            return buffer.toString();
        } else {
            return this.inputSource.getURI();
        }
    }



    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    public Serializable getKey() {
        return this.lexiconSource.getURI();
    }



    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        return this.lexiconSource.getValidity();
    }



  public OkResponseCache getOkResponseCache() {
    if (responseCache instanceof HttpResponseCache) {
      return ((HttpResponseCache) responseCache).okResponseCache;
    } else if (responseCache != null) {
      return new OkResponseCacheAdapter(responseCache);
    } else {
      return null;
    }
  }



    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     */
    
    public void pluginInitialize() {
        this.initTelephonyReceiver();
    }



    
    void connectionPrimed() {
        sockKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }



  
  public Execution setArguments(Object args) {
    if (args == null) {
      throw new IllegalArgumentException(
          LocalizedStrings.ExecuteRegionFunction_THE_INPUT_0_FOR_THE_EXECUTE_FUNCTION_REQUEST_IS_NULL
              .toLocalizedString("args"));
    }
    return new MultiRegionFunctionExecutor(this, args);
  }



  
  public Execution setArguments(Object args) {
    if (args == null) {
      throw new FunctionException(
          LocalizedStrings.ExecuteRegionFunction_THE_INPUT_0_FOR_THE_EXECUTE_FUNCTION_REQUEST_IS_NULL
              .toLocalizedString("args"));
    }
    return new ServerFunctionExecutor(this, args);
  }



  /**
   * This method returns set of all the entries of this PartitionedRegion(locally or remotely).
   * Currently, it throws UnsupportedOperationException
   * 
   * @param recursive boolean flag to indicate whether subregions should be considered or not.
   * @return set of all the entries of this PartitionedRegion
   * 
   *         OVERRIDES
   */
  
  public Set entrySet(boolean recursive) {
    checkReadiness();
    return Collections.unmodifiableSet(new PREntriesSet());
  }



  public Set<Region.Entry> entrySet(Set<Integer> bucketIds) {
    return new PREntriesSet(bucketIds);
  }



  @RequestMapping(method = RequestMethod.GET, value = "/version/full")
  @ResponseBody
  public String fullVersion() {
    return GemFireVersion.asString();
  }



  /**
   * @return the scope ID which can be associated with the scope
   */
  int associateScopeID() {
    return ++this.scopeNum;
  }



  public Set entrySet(boolean recursive) {
    return this.region.entrySet(recursive);
  }



  
  public Execution setArguments(Object args) {
    if (args == null) {
      throw new FunctionException(
          LocalizedStrings.ExecuteRegionFunction_THE_INPUT_0_FOR_THE_EXECUTE_FUNCTION_REQUEST_IS_NULL
              .toLocalizedString("args"));
    }
    return new PartitionedRegionFunctionExecutor(this, args);
  }



  public void cleanUpOnIdleTaskCancel() {
    // Make sure receivers are removed from the connection table, this should always be a noop, but
    // is done here as a failsafe.
    if (isReceiver) {
      owner.removeReceiver(this);
    }
  }



  /**
   * Writes the stack trace of the Throwable to a String.
   *
   * @param t a Throwable object who's stack trace will be written to a String.
   * @return a String containing the stack trace of the Throwable.
   * @see java.io.StringWriter
   * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
   */
  private static String getPrintableStackTrace(final Throwable t) {
    final StringWriter stackTraceWriter = new StringWriter();
    t.printStackTrace(new PrintWriter(stackTraceWriter));
    return stackTraceWriter.toString();
  }



  /**
   * Peek into the JAR data and make sure that it is valid JAR content.
   * 
   * @param jarBytes Bytes of data to be validated.
   * @return True if the data has JAR content, false otherwise
   */
  public static boolean hasValidJarContent(final byte[] jarBytes) {
    return hasValidJarContent(new ByteArrayInputStream(jarBytes));
  }



  
  public Execution setArguments(Object args) {
    if (args == null) {
      throw new IllegalArgumentException(
          LocalizedStrings.ExecuteRegionFunction_THE_INPUT_0_FOR_THE_EXECUTE_FUNCTION_REQUEST_IS_NULL
              .toLocalizedString("Args"));
    }
    return new DistributedRegionFunctionExecutor(this, args);
  }



  public Set entrySet(boolean recursive) {
    checkReadiness();
    checkForNoAccess();
    return basicEntries(recursive);
  }



  
  public Long getFileSize() throws LensException {
    return fileSize;
  }



  public void addAnswerableMeasurePhraseIndices(int index) {
    answerableMeasurePhraseIndices.add(index);
  }



  
  public String getProviderName() {
    return "lens client history provider";
  }



  
  public String getProviderName() {
    return "lens prompt provider";
  }



  
  public Long getFileSize() throws LensException {
    return null;
  }



  public String getQueryHandleString() {
    return prepareHandleId.toString();
  }



  public boolean finished() {
    return status.equals(Status.SUCCESSFUL) || status.equals(Status.FAILED) || status.equals(Status.CANCELED);
  }



    public Node jjtGetChild(int i)
    {
        return children[i];
    }



    /**
     * Set the reader into error state with return code <i>rc</i>.
     *
     * @param throwable exception indicating the error
     * @param isBackground is the reader set exception by background reads or foreground reads
     */
    private void completeExceptionally(Throwable throwable, boolean isBackground) {
        lastException.compareAndSet(null, throwable);
        if (isBackground) {
            notifyReaders();
        }
    }



    
    public LogReader openLogReader(DLSN fromDLSN) throws IOException {
        return getInputStreamInternal(fromDLSN, Optional.<Long>absent());
    }



    
    public BKSyncLogWriter openLogWriter() throws IOException {
        checkClosedOrInError("startLogSegmentNonPartitioned");
        BKSyncLogWriter writer = new BKSyncLogWriter(conf, dynConf, this);
        boolean success = false;
        try {
            writer.createAndCacheWriteHandler();
            BKLogWriteHandler writeHandler = writer.getWriteHandler();
            Utils.ioResult(writeHandler.lockHandler());
            success = true;
            return writer;
        } finally {
            if (!success) {
                writer.abort();
            }
        }
    }



  /**
   * Get the singleton empty iterable
   *
   * @param <T> Element type
   * @return Singleton empty iterable
   */
  public static <T> Iterable<T> get() {
    return (Iterable<T>) EMPTY_ITERABLE;
  }



  /**
   * Get the default value of this option
   *
   * @return default value
   */
  public boolean getDefaultValue() {
    return defaultValue;
  }



  
  public void setConf(
      ImmutableClassesGiraphConfiguration conf) {
    jythonClassName = jythonClassNameOption().get(conf);
    useWrapper = conf.getValueNeedsWrappers().get(getGraphType());
  }



  
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    this.conf = conf;
    edgeValueClass = conf.getEdgeValueClass();
  }



  /**
   * Are all the vertices halted?
   *
   * @return True if all halted, false otherwise
   */
  public boolean allVerticesHalted() {
    return allVerticesHalted;
  }



  
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    this.conf = conf;
    vertexIdClass = conf.getVertexIdClass();
  }



  
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    this.conf = conf;
    vertexValueClass = conf.getVertexValueClass();
  }



	public synchronized void terminateConsole(String appName) throws Exception {
		ApplicationLogConsole console = getExisitingConsole(runTarget.getTargetProperties(), appName);
		if (console != null) {
			console.close();
			console.destroy();
		}
	}



	/**
	 * Refreshes the model from XML and then controller and view.
	 */
	protected void refreshAll() {
		ActivityDiagramPart part = (ActivityDiagramPart) getGraphicalViewer().getContents();
		if (part != null && part.isActive()) {
			part.refreshAll();
		}
	}



	public static ApplicationYamlAssistContext subdocument(int documentSelector, FuzzyMap<PropertyInfo> index, PropertyCompletionFactory completionFactory, TypeUtil typeUtil, RelaxedNameConfig conf) {
		return new IndexContext(documentSelector, YamlPath.EMPTY, IndexNavigator.with(index), completionFactory, typeUtil, conf);
	}



	
	public boolean suggestUrl(CloudFoundryApplicationModule appModule) {
		return ProjectUtils.isSpringBootProject(appModule);
	}



	/**
	 *
	 * @param e
	 * @return true if request error should be treated as an error and thrown. False error
	 *         should be ignored.
	 */
	public boolean throwError(Throwable e) {
		return true;
	}



	private void limitExceeded(String reason) {
		this.limitExceeded = true;
		throw new SessionLimitExceededException(reason, CloseStatus.SESSION_NOT_RELIABLE);
	}



	private static <T> T result(@Nullable T result) {
		Assert.state(result != null, "No result");
		return result;
	}



	/**
	 * Asserts that this request has not been {@linkplain #execute() executed} yet.
	 *
	 * @throws IllegalStateException if this request has been executed
	 */
	protected void assertNotExecuted() {
		Assert.state(!this.executed, "ClientHttpRequest already executed");
	}



	/**
	 * Create a rae TransactionStatus instance for the given arguments.
	 */
	protected DefaultTransactionStatus newTransactionStatus(
			TransactionDefinition definition, Object transaction, boolean newTransaction,
			boolean newSynchronization, boolean debug, Object suspendedResources) {

		boolean actualNewSynchronization = newSynchronization &&
				!TransactionSynchronizationManager.isSynchronizationActive();
		return new DefaultTransactionStatus(
				transaction, newTransaction, actualNewSynchronization,
				definition.isReadOnly(), debug, suspendedResources);
	}



	/**
	 * If this type is a {@link Map}, creates a mapKey {@link TypeDescriptor} from the provided map key.
	 * Narrows the {@link #getMapKeyTypeDescriptor() mapKeyType} property to the class of the provided map key.
	 * For example, if this describes a java.util.Map&lt;java.lang.Number, java.lang.String&lt; and the key argument is a java.lang.Integer, the returned TypeDescriptor will be java.lang.Integer.
	 * If this describes a java.util.Map&lt;?, ?&gt; and the key argument is a java.lang.Integer, the returned TypeDescriptor will be java.lang.Integer as well.
	 * Annotation and nested type context will be preserved in the narrowed TypeDescriptor that is returned. 
	 * @param mapKey the map key
	 * @return the map key type descriptor
	 * @throws IllegalStateException if this type is not a java.util.Map.
	 * @see #narrow(Object)
	 */
	public TypeDescriptor getMapKeyTypeDescriptor(Object mapKey) {
		return narrow(mapKey, getMapKeyTypeDescriptor());
	}



	/**
	 * If this type is a {@link Map}, creates a mapValue {@link TypeDescriptor} from the provided map value.
	 * Narrows the {@link #getMapValueTypeDescriptor() mapValueType} property to the class of the provided map value.
	 * For example, if this describes a java.util.Map&lt;java.lang.String, java.lang.Number&lt; and the value argument is a java.lang.Integer, the returned TypeDescriptor will be java.lang.Integer.
	 * If this describes a java.util.Map&lt;?, ?&gt; and the value argument is a java.lang.Integer, the returned TypeDescriptor will be java.lang.Integer as well.
	 * Annotation and nested type context will be preserved in the narrowed TypeDescriptor that is returned. 
	 * @param mapValue the map value
	 * @return the map value type descriptor
	 * @throws IllegalStateException if this type is not a java.util.Map. 
	 */
	public TypeDescriptor getMapValueTypeDescriptor(Object mapValue) {
		return narrow(mapValue, getMapValueTypeDescriptor());		
	}



	/**
	 * Finds a {@code VersionStrategy} for the request path of the requested resource.
	 * @return an instance of a {@code VersionStrategy} or null if none matches that request path
	 */
	protected VersionStrategy getStrategyForPath(String requestPath) {
		String path = "/".concat(requestPath);
        List<String> matchingPatterns = new ArrayList<String>();
		for (String pattern : this.versionStrategyMap.keySet()) {
			if (this.pathMatcher.match(pattern, path)) {
                matchingPatterns.add(pattern);
			}
		}
        if (!matchingPatterns.isEmpty()) {
            Comparator<String> comparator = this.pathMatcher.getPatternComparator(path);
            Collections.sort(matchingPatterns, comparator);
            return this.versionStrategyMap.get(matchingPatterns.get(0));
        }

		return null;
	}



	/**
	 * Called when a data item is received via {@link Subscriber#onNext(Object)}.
	 * The default implementation saves the data for writing when possible.
	 */
	protected void dataReceived(T data) {
		if (this.currentData != null) {
			throw new IllegalStateException("Current data not processed yet: " + this.currentData);
		}
		this.currentData = data;
	}



	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}



	private void updateDepth(JsonToken token) {
		switch (token) {
			case START_OBJECT:
				this.objectDepth++;
				break;
			case END_OBJECT:
				this.objectDepth--;
				break;
			case START_ARRAY:
				this.arrayDepth++;
				break;
			case END_ARRAY:
				this.arrayDepth--;
				break;
		}
	}



	private <T> Consumer<T> updateConnectMono(MonoProcessor<Void> connectMono) {
		return o -> {
			if (!connectMono.isTerminated()) {
				if (o instanceof Throwable) {
					connectMono.onError((Throwable) o);
				}
				else {
					connectMono.onComplete();
				}
			}
		};
	}



	private void addMetadataFor(Element element) {
		Set<String> stereotypes = new LinkedHashSet<>();
		this.stereotypesProviders.forEach(p -> stereotypes.addAll(p.getStereotypes(element)));
		if (!stereotypes.isEmpty()) {
			this.metadataCollector.add(new ItemMetadata(this.typeHelper.getType(element), stereotypes));
		}
	}



	
	protected void applyCookies() {
		getCookies().values().stream().flatMap(Collection::stream)
				.map(cookie -> new DefaultCookie(cookie.getName(), cookie.getValue()))
				.forEach(this.httpRequest::addCookie);
	}



	
	protected void applyHeaders() {
		getHeaders().entrySet()
				.forEach(e -> this.httpRequest.requestHeaders().set(e.getKey(), e.getValue()));
	}



	private HttpHeaders toHttpHeaders(HttpClientResponse response) {
		HttpHeaders headers = new HttpHeaders();
		response.responseHeaders().forEach(entry -> {
			String name = entry.getKey();
			headers.put(name, response.responseHeaders().getAll(name));
		});
		return headers;
	}



	/**
	 * Requires {@link TilesView}.
	 */
	
	protected Class<?> requiredViewClass() {
		return TilesView.class;
	}



	@Nullable
	private SpelNodeImpl eatNonDottedNode() {
		if (peekToken(TokenKind.LSQUARE)) {
			if (maybeEatIndexer()) {
				return pop();
			}
		}
		return null;
	}



	private void updateBindingContext(BindingContext context, ServerWebExchange exchange) {
		Map<String, Object> model = context.getModel().asMap();
		model.keySet().stream()
				.filter(name -> isBindingCandidate(name, model.get(name)))
				.filter(name -> !model.containsKey(BindingResult.MODEL_KEY_PREFIX + name))
				.forEach(name -> {
					WebExchangeDataBinder binder = context.createDataBinder(exchange, model.get(name), name);
					model.put(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
				});
	}



	/**
	 * This implementation always returns
	 * {@link CallableProcessingInterceptor#RESULT_NONE RESULT_NONE}.
	 */
	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
		return RESULT_NONE;
	}



	/**
	 * Sets the name of the embedded database
	 * Defaults to 'testdb' if not called.
	 * @param databaseName the database name
	 * @return this, for fluent call chaining
	 */
	public EmbeddedDatabaseBuilder setName(String databaseName) {
		this.databaseFactory.setDatabaseName(databaseName);
		return this;
	}



	/**
	 * Sets the type of embedded database.
	 * Defaults to HSQL if not called.
	 * @param databaseType the database type
	 * @return this, for fluent call chaining
	 */
	public EmbeddedDatabaseBuilder setType(EmbeddedDatabaseType databaseType) {
		this.databaseFactory.setDatabaseType(databaseType);
		return this;
	}



	private static OptionalLong lengthOf(Resource resource) {
		// Don't consume InputStream...
		if (InputStreamResource.class != resource.getClass()) {
			try {
				return OptionalLong.of(resource.contentLength());
			}
			catch (IOException ignored) {
			}
		}
		return OptionalLong.empty();
	}



	/**
	 * Configure the given interceptors for this message channel,
	 * adding them to the channel's current list of interceptors.
	 * @since 4.3.12
	 */
	public ChannelRegistration interceptors(ChannelInterceptor... interceptors) {
		this.interceptors.addAll(Arrays.asList(interceptors));
		return this;
	}



	/**
	 * Eagerly initialize Locales if necessary.
	 * @see #setLocalesToInitialize
	 */
	public void afterPropertiesSet() throws BeansException {
		if (this.localesToInitialize != null) {
			for (Locale locale : this.localesToInitialize) {
				initFactory(locale);
			}
		}
	}



	
	public final DataBufferFactory bufferFactory() {
		return this.dataBufferFactory;
	}



	/**
	 * Process the specified {@link ApplicationEvent}, checking if the condition
	 * match and handling non-null result, if any.
	 */
	public void processEvent(ApplicationEvent event) {
		Object[] args = resolveArguments(event);
		if (shouldHandle(event, args)) {
			Object result = doInvoke(args);
			if (result != null) {
				handleResult(result);
			}
			else {
				logger.trace("No result object given - no result to handle");
			}
		}
	}



	/**
	 * Kick off bean registration automatically when deployed in an {@code ApplicationContext}.
	 * @see #registerBeans()
	 */
	
	public void afterSingletonsInstantiated() {
		try {
			logger.info("Registering beans for JMX exposure on startup");
			registerBeans();
			registerNotificationListeners();
		}
		catch (RuntimeException ex) {
			// Unregister beans already registered by this exporter.
			unregisterNotificationListeners();
			unregisterBeans();
			throw ex;
		}
	}



	
	public Mono<ServerResponse> syncBody(Object body) {
		Assert.notNull(body, "'body' must not be null");
		Assert.isTrue(!(body instanceof Publisher), "Please specify the element class by using " +
				"body(Publisher, Class)");

		return new DefaultEntityResponseBuilder<>(body,
				BodyInserters.fromObject(body))
				.headers(this.headers)
				.status(this.statusCode)
				.build()
				.map(entityResponse -> entityResponse);
	}



	
	public boolean isStarted() {
		return ((this.asyncContext != null) && this.request.getServletRequest().isAsyncStarted());
	}



	private void doMarshal(Object graph, Marshaller marshaller) {
		try {
			customizeMarshaller(marshaller);
			marshaller.marshal(graph);
		}
		catch (XMLException ex) {
			throw convertCastorException(ex, true);
		}
	}



	private boolean isDisconnectedClientError(Throwable ex)  {
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		message = (message != null ? message.toLowerCase() : "");
		String className = ex.getClass().getSimpleName();
		return (message.contains("broken pipe") || DISCONNECTED_CLIENT_EXCEPTIONS.contains(className));
	}



	
	protected void applyHeaders() {
		for (Map.Entry<String, List<String>> entry : getHeaders().entrySet()) {
			HttpString headerName = HttpString.tryFromString(entry.getKey());
			this.exchange.getResponseHeaders().addAll(headerName, entry.getValue());
		}
	}



	/**
	 * Pre-initialize the factory from the XML file.
	 * Only effective if caching is enabled.
	 */
	public void afterPropertiesSet() throws BeansException {
		if (isCache()) {
			initFactory();
		}
	}



	private String attributeValueToString(Object value) {
		if (value instanceof Object[]) {
			return "[" + StringUtils.arrayToDelimitedString((Object[]) value, ", ") + "]";
		}
		return String.valueOf(value);
	}



	/**
	 * Clear {@linkplain #getConcurrentResult() concurrentResult} and
	 * {@linkplain #getConcurrentResultContext() concurrentResultContext}.
	 */
	public void clearConcurrentResult() {
		this.concurrentResult = RESULT_NONE;
		this.concurrentResultContext = null;
	}



	private static void appendSeparatorToScriptIfNecessary(StringBuilder scriptBuilder, String separator) {
		if (separator == null) {
			return;
		}
		String trimmed = separator.trim();
		if (trimmed.length() == separator.length()) {
			return;
		}
		// separator ends in whitespace, so we might want to see if the script is trying
		// to end the same way
		if (scriptBuilder.lastIndexOf(trimmed) == scriptBuilder.length() - trimmed.length()) {
			scriptBuilder.append(separator.substring(trimmed.length()));
		}
	}



	/**
	 * Determine if the specified include {@link TypeFilter} is supported by the index.
	 * @param filter the filter to check
	 * @return whether the index supports this include filter
	 * @since 5.0
	 * @see #extractStereotype(TypeFilter)
	 */
	private boolean indexSupportsIncludeFilter(TypeFilter filter) {
		if (filter instanceof AnnotationTypeFilter) {
			Class<? extends Annotation> annotation = ((AnnotationTypeFilter) filter).getAnnotationType();
			return (AnnotationUtils.isAnnotationDeclaredLocally(Indexed.class, annotation) ||
					annotation.getName().startsWith("javax."));
		}
		if (filter instanceof AssignableTypeFilter) {
			Class<?> target = ((AssignableTypeFilter) filter).getTargetType();
			return AnnotationUtils.isAnnotationDeclaredLocally(Indexed.class, target);
		}
		return false;
	}



    /**
     * Stop the Plugin, closing the QMF Connection and logging "QMF2 Management Stopped".
     */
    @StateTransition( currentState = State.ACTIVE, desiredState = State.STOPPED )
    private void doStop()
    {
        // When the Plugin state gets set to STOPPED we close the QMF Connection.
        if (_agent != null)
        {
            _agent.close();
        }

        // Log "QMF2 Management Stopped" message (may not get displayed).
        getBroker().getEventLogger().message(ManagementConsoleMessages.STOPPED(OPERATIONAL_LOGGING_NAME));
    }



    private boolean anyFilterMatchesDescription( Collection<Filter> filters, Description description )
    {
        for ( Filter f : filters )
        {
            if ( f.shouldRun( description ) )
            {
                return true;
            }
        }
        return false;
    }



    public void println( String message )
    {
        systemOut.println( message );
    }



    /**
     * Calc processing stats
     */
    public boolean doStatistics() {
        return doProcessingStats;
    }



    public AbstractRxTask createRxTask() {
        return getReplicationThread();
    }



    public boolean isUseEquals() {
        return useEquals;
    }



    /**
     * Disable swallowing of remaining input if configured
     */
    protected void checkSwallowInput() {
        Context context = getContext();
        if (context != null && !context.getSwallowAbortedUploads()) {
            coyoteRequest.action(ActionCode.DISABLE_SWALLOW_INPUT, null);
        }
    }



    public void setEventSubType(EventSubType eventSubType) {
        this.eventSubType = eventSubType;
    }



    public ServletRegistration getServletRegistration(String servletName) {
        return null;
    }



    public FilterRegistration getFilterRegistration(String filterName) {
        return null;
    }



    /**
     * Return true if bytes are available.
     */
    public boolean getAvailable() {
        return (inputBuffer.available() > 0);
    }



    public void heartbeat() {
        if ( clusterSender!=null ) clusterSender.heartbeat();
        super.heartbeat();
    }



    private int blockingRead(UpgradeProcessor<?> processor)
            throws IOException {
        int result = processor.read();
        if (result == -1) {
            throw new IOException(sm.getString("frame.eos"));
        }
        return result;
    }



    private void blockingRead(UpgradeProcessor<?> processor, ByteBuffer bb)
            throws IOException {
        int last = 0;
        while (bb.hasRemaining()) {
            last = processor.read();
            if (last == -1) {
                throw new IOException(sm.getString("frame.eos"));
            }
            bb.put((byte) (last ^ mask[bb.position() % 4]));
        }
        bb.flip();
    }



    protected void checkExpiration() {
        long timeout = maxIdleTimeout;
        if (timeout < 1) {
            return;
        }

        if (System.currentTimeMillis() - lastActive > timeout) {
            String msg = sm.getString("wsSession.timeout");
            doClose(new CloseReason(CloseCodes.GOING_AWAY, msg),
                    new CloseReason(CloseCodes.CLOSED_ABNORMALLY, msg));
        }
    }



    public AbstractRxTask createRxTask() {
        NioReplicationTask thread = new NioReplicationTask(this,this);
        thread.setUseBufferPool(this.getUseBufferPool());
        thread.setRxBufSize(getRxBufSize());
        thread.setOptions(getWorkerThreadOptions());
        return thread;
    }



    /**
     * Sets up the connection pool, by creating a pooling driver.
     * @return Driver
     * @throws SQLException
     */
    private synchronized ConnectionPool pCreatePool() throws SQLException {
        if (pool != null) {
            return pool;
        } else {
            pool = new ConnectionPool(poolProperties);
            return pool;
        }
    }



  /**
   * Creates a {@link Named} annotation with {@code name} as the value.
   */
  public static Named named(String name) {
    return new NamedImpl(name);
  }



    public void addModules(List<Module> modules) {
        for (Module module : modules) {
            addModuleInternal(module);
        }
        for (Module module : modules) {
            module.initialize(this);
        }
    }



    /**
     * {@inheritDoc}
     */
    public void element(XMLElement element) {
        // on parsed stanzas
        serverRuntimeContext.getStanzaProcessor().processStanza(serverRuntimeContext, this, (Stanza) element, sessionStateHolder);
    }



    public final Reader chain(final Reader rdr) {
        StripLineComments newFilter = new StripLineComments(rdr);
        newFilter.setComments(getComments());
        newFilter.setInitialized(true);
        return newFilter;
    }



    /**
     * Factory method.
     */
    public static FileUtils newFileUtils() {
        return new FileUtils();
    }



    public final Reader chain(final Reader rdr) {
        StripJavaComments newFilter = new StripJavaComments(rdr);
        return newFilter;
    }



    public final Reader chain(final Reader rdr) {
        TabsToSpaces newFilter = new TabsToSpaces(rdr);
        newFilter.setTablength(getTablength());
        newFilter.setInitialized(true);
        return newFilter;
    }



    private void internalSetValue(Object value) {
        this.untypedValue = value;
        //preserve protected string value for subclasses :(
        this.value = value == null ? null : value.toString();
    }



    /**
     * Supports grand-children that want to support the attribute
     * where the child-class doesn't (i.e. Unzip in the compress
     * Antlib).
     *
     * @since Ant 1.8.0
     */
    protected void internalSetEncoding(String encoding) {
        if (NATIVE_ENCODING.equals(encoding)) {
            encoding = null;
        }
        this.encoding = encoding;
    }



    /**
     * Supports grand-children that want to support the attribute
     * where the child-class doesn't (i.e. Unzip in the compress
     * Antlib).
     *
     * @since Ant 1.8.0
     */
    protected void internalSetScanForUnicodeExtraFields(boolean b) {
        scanForUnicodeExtraFields = b;
    }



    public final Reader chain(final Reader rdr) {
        PrefixLines newFilter = new PrefixLines(rdr);
        newFilter.setPrefix(getPrefix());
        newFilter.setInitialized(true);
        return newFilter;
    }



    /**
     * API method to set the XSL Resource.
     * @param xslResource Resource to set as the stylesheet.
     * @since Ant 1.7
     */
    public void setXslResource(Resource xslResource) {
        this.xslResource = xslResource;
    }



    public final Reader chain(final Reader rdr) {
        StripLineBreaks newFilter = new StripLineBreaks(rdr);
        newFilter.setLineBreaks(getLineBreaks());
        newFilter.setInitialized(true);
        return newFilter;
    }



    public final Reader chain(final Reader rdr) {
        ReplaceTokens newFilter = new ReplaceTokens(rdr);
        newFilter.setBeginToken(getBeginToken());
        newFilter.setEndToken(getEndToken());
        newFilter.setTokens(getTokens());
        newFilter.setInitialized(true);
        return newFilter;
    }



    public void completed() throws StreamException {
        try {
            contentHandler.endDocument();
        } catch (SAXException ex) {
            throw new StreamException(ex);
        }
    }



    public void endElement() {
        target = ((OMNode)target).getParent();
    }



    public static Builder builder() {
        return new Builder();
    }



    
    public void processAttribute(String namespaceURI, String localName, String prefix, String value,
            String type, boolean specified) {
        OMElement element = (OMElement)target;
        OMNamespace ns;
        if (namespaceURI.length() > 0) {
            ns = element.findNamespace(namespaceURI, prefix);
            if (ns == null) {
                throw new OMException("Unbound namespace " + namespaceURI);
            }
        } else {
            ns = null;
        }
        OMAttribute attr = element.addAttribute(localName, value, ns);
        attr.setAttributeType(type);
    }



    public void processEntityReference(String name, String replacementText) {
        if (replacementText == null) {
            factory.createOMEntityReference(target, name);
        } else {
            // Since we set expandEntityReferences=true, we should never get here
            throw new UnsupportedOperationException();
        }
    }



    
    public void processNamespaceDeclaration(String prefix, String namespaceURI) {
        if (prefix.isEmpty()) {
            ((OMElement)target).declareDefaultNamespace(namespaceURI);
        } else {
            ((OMElement)target).declareNamespace(namespaceURI, prefix);
        }
    }



    public void processDocumentTypeDeclaration(String rootName, String publicId,
            String systemId, String internalSubset) {
        if (target instanceof OMDocument) {
            factory.createOMDocType(target, rootName, publicId, systemId, internalSubset);
        }
    }



    /**
     * Replaces a property.
     * 
     * @param property
     *            the property
     */
    public void replaceProperty(PropertyData<?> property) {
        if ((property == null) || (property.getId() == null)) {
            return;
        }

        removeProperty(property.getId());

        propertyList.add(property);
        properties.put(property.getId(), property);
    }



    /**
     * Returns UTF-8 bytes of the given string or throws a
     * {@link CmisRuntimeException} if the charset 'UTF-8' is not available.
     * 
     * @param s
     *            the input string
     * 
     * @return the UTF-8 bytes
     */
    public static byte[] toUTF8Bytes(String s) {
        if (s == null) {
            return null;
        }

        try {
            return s.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new CmisRuntimeException("Unsupported encoding 'UTF-8'!", e);
        }
    }



    public void unregister() {
        if (alreadyRegistered) {
            httpService.unregister(alias);
            alreadyRegistered = false;
        }
    }



    
    public int getSize() {
        return registry.size();
    }



	private void shutdownExecutor() {
	    if (executor != null) {
            endpoint.getCamelContext().getExecutorServiceManager().shutdownNow(executor);
            executor = null;
        }
    }



    public Boolean getRoundRobin() {
        return roundRobin;
    }



    public <T> Map<String, T> findByTypeWithName(Class<T> type) {
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Object> entry : entrySet()) {
            if (type.isInstance(entry.getValue())) {
                result.put(entry.getKey(), type.cast(entry.getValue()));
            }
        }
        return result;
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    private static void doAssertMatches(Predicate predicate, String text, Exchange exchange) {
        if (!predicate.matches(exchange)) {
            if (text == null) {
                throw new AssertionError(predicate + " on " + exchange);
            } else {
                throw new AssertionError(text + predicate + " on " + exchange);
            }
        }
    }



    
    public <T> Map<String, T> findByTypeWithName(Class<T> type) {
        return lookupByType(blueprintContainer, type);
    }



    
    protected void doStart() throws Exception {
        if (headerFilterStrategy == null) {
            headerFilterStrategy = new CxfHeaderFilterStrategy();
        }
        if (cxfBinding == null) {
            cxfBinding = new DefaultCxfBinding();
        }
        if (cxfBinding instanceof HeaderFilterStrategyAware) {
            ((HeaderFilterStrategyAware)cxfBinding).setHeaderFilterStrategy(getHeaderFilterStrategy());
        }
    }



    public Boolean isAllowStreaming() {
        return allowStreaming;
    }



    protected <T> void doAddOption(Map<String, T> options, String name, T value) {
        log.trace("Adding option: {}={}", name, value);
        T val = options.put(name, value);
        if (val != null) {
            log.debug("Options {} overridden, old value was {}", name, val);
        }
    }



    /**
     * Configures the startup order for this route
     * <p/>
     * Camel will reorder routes and star them ordered by 0..N where 0 is the lowest number and N the highest number.
     * Camel will stop routes in reverse order when its stopping.
     *
     * @param order the order represented as a number
     * @return this builder
     */
    public RouteDefinition startupOrder(int order) {
        setStartupOrder(order);
        return this;
    }



    public boolean isListChildren() {
        return listChildren;
    }



    public boolean isUseBlockIO() {
        return useBlockIO;
    }



    protected void doStop() throws Exception {
        ServiceHelper.stopService(assembler);
    }



    
    public void setHeader(String name, String value) {
        // use set header to avoid adding the same value multiple times (the method should have been named setHeader really)
        response.setHeader(name, value);
    }



    void addConsumer(CdiEventConsumer<T> consumer) {
        synchronized (consumers) {
            consumers.add(consumer);
        }
    }



    void removeConsumer(CdiEventConsumer<T> consumer) {
        synchronized (consumers) {
            consumers.remove(consumer);
        }
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    public Boolean getMarkRollbackOnlyLast() {
        return markRollbackOnlyLast;
    }



    public Boolean getMarkRollbackOnly() {
        return markRollbackOnly;
    }



    private String doGetEndpointId(Endpoint ep) {
        if (ep.isSingleton()) {
            return ep.getEndpointKey();
        } else {
            // non singleton then add hashcoded id
            String uri = ep.getEndpointKey();
            int pos = uri.indexOf('?');
            String id = (pos == -1) ? uri : uri.substring(0, pos);
            id += "?id=" + ObjectHelper.getIdentityHashCode(ep);
            return id;
        }
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    
    protected void doStop() throws Exception {
        ServiceHelper.stopService(logger);
    }



    
    public Map<String, Object> newMap(Map<String, Object> map) {
        return new CaseInsensitiveMap(map);
    }



    public Boolean getStopOnException() {
        return stopOnException;
    }



    public Boolean getIgnoreInvalidEndpoints() {
        return ignoreInvalidEndpoints;
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    
    public String getMessageGroupId(Exchange exchange) {
        return "CamelSingleMessageGroup";
    }



    public Boolean getCustomId() {
        return customId;
    }



    
    public void doStop() throws Exception {
        pool.clear();
        poolLogNamespaces.clear();
    }



    protected void setupVmProtocol(String uri, Mina2Configuration configuration) {

        boolean minaLogger = configuration.isMinaLogger();
        List<IoFilter> filters = configuration.getFilters();

        address = new VmPipeAddress(configuration.getPort());
        acceptor = new VmPipeAcceptor();

        // acceptor connectorConfig
        configureCodecFactory("Mina2Consumer", acceptor, configuration);
        if (minaLogger) {
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        }
        appendIoFiltersToChain(filters, acceptor.getFilterChain());
        if (configuration.getSslContextParameters() != null) {
            LOG.warn("Using vm protocol"
                     + ", but an SSLContextParameters instance was provided.  SSLContextParameters is only supported on the TCP protocol.");
        }
    }



    public Boolean getSupportExtendedInformation() {
        return false;
    }



    public Boolean getValidation() {
        return validation;
    }



    /**
     * Marks the route definition as prepared.
     * <p/>
     * This is needed if routes have been created by components such as
     * <tt>camel-spring</tt> or <tt>camel-blueprint</tt>.
     * Usually they share logic in the <tt>camel-core-xml</tt> module which prepares the routes.
     */
    public void markPrepared() {
        prepared.set(true);
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    /**
     * Subclasses can override to prevent the jms configuration from being
     * setup to use an auto-wired the destination resolved that's found in the spring
     * application context.
     *
     * @return true by default
     */
    public boolean isAllowAutoWiredDestinationResolver() {
        return true;
    }



    /**
     * Subclasses can override to prevent the jms configuration from being
     * setup to use an auto-wired the connection factory that's found in the spring
     * application context.
     *
     * @return true by default
     */
    public boolean isAllowAutoWiredConnectionFactory() {
        return true;
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    
    protected void doStart() throws Exception {
        server.start();
    }



    /**
     * Populates soap message headers and attachments from soap response
     * 
     * @param inOrOut
     *            {@link Message}
     * @param soapMessage
     *            {@link SoapMessage}
     */
    private void populateHeaderAndAttachmentsFromResponse(Message inOrOut, SoapMessage soapMessage) {
        if (soapMessage.getSoapHeader() != null && getEndpoint().getConfiguration().isAllowResponseHeaderOverride()) {
            populateMessageHeaderFromResponse(inOrOut, soapMessage.getSoapHeader());
        }
        if (soapMessage.getAttachments() != null && getEndpoint().getConfiguration().isAllowResponseAttachmentOverride()) {
            populateMessageAttachmentsFromResponse(inOrOut, soapMessage.getAttachments());
        }
    }



    
    protected void doStop() throws Exception {
        ServiceHelper.stopService(loadBalancer);
    }



    @Converter
    public static String toString(Record record) {
        return record.toString();
    }



    @Converter
    public static String toString(InetAddress address) {
        return address.getHostAddress();
    }



    @Converter
    public static String toString(Message message) {
        return message.toString();
    }



    @Converter
    public static String toString(Address address) {
        return address.toString();
    }



    public boolean isSessionHeadersEnabled() {
        return sessionHeadersEnabled;
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    public Boolean getRoundRobin() {
        return roundRobin;
    }



    /**
     * Creates a try/catch block
     *
     * @return the builder for a tryBlock expression
     */
    public TryDefinition doTry() {
        TryDefinition answer = new TryDefinition();
        addOutput(answer);
        return answer;
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    
    public void setHeader(String name, String value) {
        exchange.getIn().setHeader(name, value);
    }



    /**
     * Returns an expression processing the exchange to the given endpoint uri.
     *
     * @param uri   endpoint uri
     * @return the builder
     */
    public static ValueBuilder sendTo(String uri) {
        Expression expression = ExpressionBuilder.toExpression(uri);
        return new ValueBuilder(expression);
    }



    public boolean hasCustomAnnotation() {
        return hasCustomAnnotation;
    }



    /**
     * The finally block for a given handle
     *
     * @return  the try builder
     */
    public TryDefinition doFinally() {
        popBlock();
        FinallyDefinition answer = new FinallyDefinition();
        addOutput(answer);
        pushBlock(answer);
        return this;
    }



    public Boolean getEager() {
        return eager;
    }



    /**
     * Determines if the Signature specific headers be cleared after signing and
     * verification. Defaults to true, and should only be made otherwise at your
     * extreme peril as vital private information such as Keys and passwords may
     * escape if unset.
     *
     * @return true if the Signature headers should be unset, false otherwise
     */
    public boolean isClearHeaders() {
        return clearHeaders;
    }



    private boolean doSend(Exchange exchange, AsyncCallback callback) {
        ExecutorService executorService = this.executor;
        if (executorService != null && this.isRunAllowed()) {

            executorService.execute(() -> this.getAsyncProcessor().process(exchange, doneSync -> {
                if (exchange.getException() != null) {
                    getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
                }

                callback.done(doneSync);
            }));
            return false;

        } else {
            LOG.warn("Consumer not ready to process exchanges. The exchange {} will be discarded", exchange);
            callback.done(true);
            return true;
        }
    }



    
    public String getHalfOpenHandlerName() {
        ThrottlingExceptionHalfOpenHandler obj = getPolicy().getHalfOpenHandler();
        if (obj != null) {
            return obj.getClass().getSimpleName();
        } else {
            return "";
        }
    }



    public boolean hasCustomListener() {
        return customListener != null;
    }



    public Boolean getCopy() {
        return copy;
    }



    public boolean isAllowNullValues() {
        return allowNullValues;
    }



    public void addAllSniHostNames(List<String> sniHostNames) {
        for (String sniHostName : sniHostNames) {
            this.sniHostNames.add(new SNIHostName(sniHostName));
        }
    }



    public RestOperationResponseMsgDefinition responseMessage(VerbDefinition verb) {
        return new RestOperationResponseMsgDefinition(verb);
    }



    public RestOperationParamDefinition param(VerbDefinition verb) {
        return new RestOperationParamDefinition(verb);
    }



    public Boolean getEagerCheckCompletion() {
        return eagerCheckCompletion;
    }



    public Boolean getDiscardOnCompletionTimeout() {
        return discardOnCompletionTimeout;
    }



    public Boolean getIgnoreInvalidCorrelationKeys() {
        return ignoreInvalidCorrelationKeys;
    }



    public Boolean getGroupExchanges() {
        return groupExchanges;
    }



    public Boolean getCompletionFromBatchConsumer() {
        return completionFromBatchConsumer;
    }



    
    public Boolean getSupportExtendedInformation() {
        return true;
    }



    public static <R, T extends Throwable> R supplyWithReadLockT(StampedLock lock, ThrowingSupplier<R, T> task) throws T {
        long stamp = lock.readLock();

        try {
            return task.get();
        } finally {
            lock.unlockRead(stamp);
        }
    }



    public static <R> R supplyWithReadLock(StampedLock lock, Supplier<R> task)  {
        long stamp = lock.readLock();

        try {
            return task.get();
        } finally {
            lock.unlockRead(stamp);
        }
    }



    public static <R> R supplyWithWriteLock(StampedLock lock, Supplier<R> task)  {
        long stamp = lock.writeLock();

        try {
            return task.get();
        } finally {
            lock.unlockWrite(stamp);
        }
    }



    public static <R, T extends Throwable> R supplyWithWriteLockT(StampedLock lock, ThrowingSupplier<R, T> task) throws T {
        long stamp = lock.writeLock();

        try {
            return task.get();
        } finally {
            lock.unlockWrite(stamp);
        }
    }



    public Boolean getValidate() {
        return validate;
    }



    /**
     * Refers to a Hystrix configuration to use for configuring the Hystrix EIP.
     */
    public HystrixDefinition hystrixConfiguration(String ref) {
        hystrixConfigurationRef = ref;
        return this;
    }



    /**
     * Configures the Hystrix EIP using the given configuration
     */
    public HystrixDefinition hystrixConfiguration(HystrixConfigurationDefinition configuration) {
        hystrixConfiguration = configuration;
        return this;
    }



	/**
	 * Simply convert to a String with toString().
	 * 
	 * @see org.springframework.batch.item.file.transform.LineAggregator#aggregate(java.lang.Object)
	 */
	public String aggregate(T item) {
		return item.toString();
	}



	
	public List<JobInstance> findJobInstancesByJobName(String jobName, int start, int count) {
		return jobInstanceDao.findJobInstancesByName(jobName, start, count);
	}



	/**
	 * On unsuccessful execution after a chunk has rolled back.
	 */
	public synchronized void incrementRollbackCount() {
		rollbackCount++;
	}



    /**
     * Initializes any configuration parameters that have not/can not be defined
     * or defaulted by the Mojo API.
     */
    private void initializeDefaultConfigurationParameters()
    {
        if ( displayFileRevDetailUrl == null || displayFileRevDetailUrl.length() == 0 )
        {
            displayFileRevDetailUrl = displayFileDetailUrl;
        }
    }



    private boolean isConfigurationDefinedInPOM( List<String> configuration )
    {
        return configuration != null && !configuration.isEmpty();
    }



    private void ifOutputDirectoryExistsDelteIt()
        throws MojoExecutionException
    {
        if ( outputDirectoryImage.exists() )
        {
            // Delete the output folder of JLink before we start
            // otherwise JLink will fail with a message "Error: directory already exists: ..."
            try
            {
                getLog().debug( "Deleting existing " + outputDirectoryImage.getAbsolutePath() );
                FileUtils.forceDelete( outputDirectoryImage );
            }
            catch ( IOException e )
            {
                getLog().error( "IOException", e );
                throw new MojoExecutionException( "Failure during deletion of " + outputDirectoryImage.getAbsolutePath()
                    + " occured." );
            }
        }
    }



  /**
   * Converts an iterable into a collection. If the iterable is already a
   * collection, it is returned. Otherwise, an {@link java.util.ArrayList} is
   * created with the contents of the iterable in the same iteration order.
   */
  private static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
    return (iterable instanceof Collection)
        ? (Collection<E>) iterable
        : Lists.newArrayList(iterable.iterator());
  }



  private String computeToString() {
    StringBuilder builder = new StringBuilder().append(type).append('/').append(subtype);
    if (!parameters.isEmpty()) {
      builder.append("; ");
      Multimap<String, String> quotedParameters = Multimaps.transformValues(parameters,
          new Function<String, String>() {
             public String apply(String value) {
              return TOKEN_MATCHER.matchesAllOf(value) ? value : escapeAndQuote(value);
            }
          });
      PARAMETER_JOINER.appendTo(builder, quotedParameters.entries());
    }
    return builder.toString();
  }



   protected boolean doEquivalent(F a, F b) {
    return resultEquivalence.equivalent(function.apply(a), function.apply(b));
  }



   protected int doHash(F a) {
    return resultEquivalence.hash(function.apply(a));
  }



  
  public ImmutableList<V> asList() {
    final ImmutableList<Entry<K, V>> entryList = map.entrySet().asList();
    return new ImmutableAsList<V>() {
      
      public V get(int index) {
        return entryList.get(index).getValue();
      }

      
      ImmutableCollection<V> delegateCollection() {
        return ImmutableMapValues.this;
      }
    };
  }



   Iterator<Cell<R, C, V>> cellIterator() {
    return new CellIterator();
  }



  /**
   * Returns an {@code ImmutableList} containing the same elements, in the same order, as this
   * collection.
   *
   * <p><b>Performance note:</b> in most cases this method can return quickly without actually
   * copying anything. The exact circumstances under which the copy is performed are undefined and
   * subject to change.
   *
   * @since 2.0
   */
  public ImmutableList<E> asList() {
    switch (size()) {
      case 0:
        return ImmutableList.of();
      case 1:
        return ImmutableList.of(iterator().next());
      default:
        return new RegularImmutableAsList<E>(this, toArray());
    }
  }



   ImmutableCollection<V> createValues() {
    return ImmutableSet.of(singleValue);
  }



  private Iterator<String> splittingIterator(CharSequence sequence) {
    return strategy.iterator(this, sequence);
  }



  
  int distinctElements() {
    return backingMap.size();
  }



  
  protected int doHash(Iterable<T> iterable) {
    int hash = 78721;
    for (T element : iterable) {
      hash = hash * 24943 + elementEquivalence.hash(element);
    }
    return hash;
  }



  
  protected boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
    Iterator<T> iteratorA = iterableA.iterator();
    Iterator<T> iteratorB = iterableB.iterator();

    while (iteratorA.hasNext() && iteratorB.hasNext()) {
      if (!elementEquivalence.equivalent(iteratorA.next(), iteratorB.next())) {
        return false;
      }
    }

    return !iteratorA.hasNext() && !iteratorB.hasNext();
  }



  /**
   * Package-private non-final implementation of andThen() so only we can override it.
   */
  <C> Converter<A, C> doAndThen(Converter<B, C> secondConverter) {
    return new ConverterComposition<A, B, C>(this, checkNotNull(secondConverter));
  }



  /**
   * Returns the name of this service. {@link AbstractIdleService} may include the name in debugging
   * output.
   *
   * @since 14.0
   */
  protected String serviceName() {
    return getClass().getSimpleName();
  }



   int distinctElements() {
    return countMap.size();
  }



  /**
   * Returns the name of this service. {@link AbstractExecutionThreadService}
   * may include the name in debugging output.
   *
   * <p>Subclasses may override this method.
   *
   * @since 14.0 (present in 10.0 as getServiceName)
   */
  protected String serviceName() {
    return getClass().getSimpleName();
  }



  private boolean someRawTypeIsSubclassOf(Class<?> superclass) {
    for (Class<?> rawType : getRawTypes()) {
      if (superclass.isAssignableFrom(rawType)) {
        return true;
      }
    }
    return false;
  }



  /**
   * Sets a custom {@code Equivalence} strategy for comparing keys.
   *
   * <p>By default, the map uses {@link Equivalences#identity} to determine key equality when
   * {@link #weakKeys} or {@link #softKeys} is specified, and {@link Equivalences#equals()}
   * otherwise.
   */
  @GwtIncompatible("To be supported")
  
  MapMaker keyEquivalence(Equivalence<Object> equivalence) {
    checkState(keyEquivalence == null, "key equivalence was already set to %s", keyEquivalence);
    keyEquivalence = checkNotNull(equivalence);
    this.useCustomMap = true;
    return this;
  }



   UnmodifiableIterator<K> keyIterator() {
    return Iterators.unmodifiableIterator(delegate.keySet().iterator());
  }



    private void decrementClaimCount(final ContentClaim claim) {
        if (claim == null) {
            return;
        }

        context.getContentRepository().decrementClaimantCount(claim);
    }



    
    public void doSchedule(final ReportingTaskNode taskNode, ScheduleState scheduleState) {
        throw new UnsupportedOperationException("ReportingTasks cannot be scheduled in Event-Driven Mode");
    }



    
    public void doUnschedule(ReportingTaskNode taskNode, ScheduleState scheduleState) {
        throw new UnsupportedOperationException("ReportingTasks cannot be scheduled in Event-Driven Mode");
    }



    
    public void doUnschedule(final Connectable connectable, final ScheduleState scheduleState) {
        workerQueue.suspendWork(connectable);
        logger.info("Stopped scheduling {} to run", connectable);
    }



    
    public void doSchedule(final Connectable connectable, final ScheduleState scheduleState) {
        workerQueue.resumeWork(connectable);
        logger.info("Scheduled {} to run in Event-Driven mode", connectable);
        scheduleStates.put(connectable, scheduleState);
    }



    
    public void doUnschedule(final Connectable connectable, final ScheduleState scheduleState) {
        for (final ScheduledFuture<?> future : scheduleState.getFutures()) {
            // stop scheduling to run but do not interrupt currently running tasks.
            future.cancel(false);
        }

        logger.info("Stopped scheduling {} to run", connectable);
    }



    
    public void doUnschedule(final ReportingTaskNode taskNode, final ScheduleState scheduleState) {
        for (final ScheduledFuture<?> future : scheduleState.getFutures()) {
            // stop scheduling to run but do not interrupt currently running tasks.
            future.cancel(false);
        }

        logger.info("Stopped scheduling {} to run", taskNode.getReportingTask());
    }



    private ControllerDTO fetchController() throws IOException {
        try {
            final HttpGet get = createGetControllerRequest();
            return execute(get, ControllerEntity.class).getController();
        } catch (final HttpGetFailedException e) {
            if (RESPONSE_CODE_NOT_FOUND == e.getResponseCode()) {
                logger.debug("getController received NOT_FOUND, trying to access the old NiFi version resource url...");
                final HttpGet get = createGet("/controller");
                return execute(get, ControllerEntity.class).getController();
            }
            throw e;
        }
    }



    
    public synchronized void doUnschedule(final Connectable connectable, final ScheduleState scheduleState) {
        unschedule((Object) connectable, scheduleState);
    }



    
    public synchronized void doUnschedule(final ReportingTaskNode taskNode, final ScheduleState scheduleState) {
        unschedule((Object) taskNode, scheduleState);
    }



	
	public boolean rendersPage()
	{
		return false;
	}



	/**
	 * @see org.apache.wicket.behavior.AbstractBehavior#afterRender(org.apache.wicket.Component)
	 */
	
	public final void afterRender(final Component hostComponent)
	{
		onComponentRendered();
	}



	/**
	 * 
	 * @param sessionId
	 * @param page
	 * @return the serialized page information
	 */
	protected SerializedPage createSerializedPage(final String sessionId, final IManageablePage page)
	{
		Args.notNull(sessionId, "sessionId");
		Args.notNull(page, "page");

		SerializedPage serializedPage = null;

		byte[] data = serializePage(page);

		if (data != null)
		{
			serializedPage = new SerializedPage(sessionId, page.getPageId(), data);
		}
		else if (LOG.isWarnEnabled())
		{
			LOG.warn("Page {} cannot be serialized. See previous logs for possible reasons.", page);
		}
		return serializedPage;
	}



	/**
	 * Searches the container stack for a component that can be dequeud
	 * 
	 * @param tag
	 * @return
	 */
	public Component findComponentToDequeue(ComponentTag tag)
	{
		for (int j = containers.size() - 1; j >= 0; j--)
		{
			MarkupContainer container = containers.get(j);
			Component child = container.findComponentToDequeue(tag);
			if (child != null)
			{
				return child;
			}
		}
		return null;
	}



	
	public boolean rendersPage()
	{
		return false;
	}



	/**
	 * @return whether {@link org.apache.wicket.resource.ITextResourceCompressor} can be used to compress the
	 *         resource.
	 */
	public boolean getCompress()
	{
		return compress;
	}



	
	public Result doCheck(Object obj)
	{
		Result result = Result.SUCCESS;

		if (obj instanceof LoadableDetachableModel<?>)
		{
			LoadableDetachableModel<?> model = (LoadableDetachableModel<?>) obj;
			if (model.isAttached())
			{
				result = new Result(Result.Status.FAILURE, "Not detached model found!");
			}
		}

		return result;
	}



	
	public boolean rendersPage()
	{
		return false;
	}



	
	public MockWebRequest cloneWithUrl(Url url)
	{
		return new MockWebRequest(url, cookies, headers, postRequestParameters, locale);
	}



	/**
	 * @param minimum
	 * @return a {@link RangeValidator} that validates if a value is a least {@code minimum}
	 */
	public static <T extends Comparable<T> & Serializable> RangeValidator<T> minimum(T minimum)
	{
		return new RangeValidator<T>(minimum, null);
	}



	/**
	 * Iterate on parent's children and set their markup to null.
	 * 
	 * @param parent
	 */
	private void cleanChildrenMarkup(MarkupContainer parent) 
	{
		for (Component child : parent) 
		{
			child.setMarkup(null);
		}
	}



	/**
	 * THIS METHOD IS NOT PART OF THE PUBLIC API, DO NOT CALL IT
	 * 
	 * Overrides {@link Component#internalInitialize()} to call {@link Component#fireInitialize()}
	 * for itself and for all its children.
	 * 
	 * @see org.apache.wicket.Component#fireInitialize()
	 */
	
	public final void internalInitialize()
	{
		super.fireInitialize();
		visitChildren(new IVisitor<Component, Void>()
		{
			public void component(final Component component, final IVisit<Void> visit)
			{
				component.fireInitialize();
			}
		});
	}



    public Calendar getCalendarValue()
    {
        check_dated();

        if (_value == null)
            return null;

        return _value.getCalendar();
    }



    public Date getDateValue()
    {
        check_dated();

        if (_value == null)
            return null;

        return _value.getDate();
    }



    public QName getQNameValue()
        { check_dated(); return _value; }



    public StringEnumAbstractBase getEnumValue()
    {
        check_dated();
        return _val;
    }



    public double getDoubleValue() { check_dated(); return _value; }



    public float getFloatValue() { check_dated(); return _value; }



    public BigDecimal getBigDecimalValue() { check_dated(); return new BigDecimal(_value); }



    public float getFloatValue() { check_dated(); return (float)_value; }



    public double getDoubleValue() { check_dated(); return _value; }



    public BigDecimal getBigDecimalValue() { check_dated(); return new BigDecimal(_value); }



    public byte[] getByteArrayValue()
    {
        check_dated();
        if (_value == null)
            return null;

        byte[] result = new byte[_value.length];
        System.arraycopy(_value, 0, result, 0, _value.length);
        return result;
    }



    public BigInteger getBigIntegerValue() { check_dated(); return _value; }



    public BigDecimal getBigDecimalValue() { check_dated(); return _value == null ? null : new BigDecimal(_value); }



    public long getLongValue() { check_dated(); return _value; }



    public BigInteger getBigIntegerValue() { check_dated(); return BigInteger.valueOf(_value); }



    public BigDecimal getBigDecimalValue() { check_dated(); return BigDecimal.valueOf(_value); }



    public BigDecimal getBigDecimalValue() { check_dated(); return _value; }



    public boolean getBooleanValue() { check_dated(); return _value; }



    public byte[] getByteArrayValue()
    {
        check_dated();
        if (_value == null)
            return null;

        byte[] result = new byte[_value.length];
        System.arraycopy(_value, 0, result, 0, _value.length);
        return result;
    }



    public int getIntValue() { check_dated(); return _value; }



    public long getLongValue() { check_dated(); return _value; }



    public BigDecimal getBigDecimalValue() { check_dated(); return new BigDecimal((double) _value); }



    public BigInteger getBigIntegerValue() { check_dated(); return BigInteger.valueOf(_value); }



    public List xgetListValue()
    {
        check_dated();
        return _value;
    }



    public List getListValue()
    {
        check_dated();
        if (_value == null)
            return null;
        if (_jvalue != null)
            return _jvalue;
        List javaResult = new ArrayList();
        for (int i = 0; i < _value.size(); i++)
            javaResult.add(java_value((XmlObject)_value.get(i)));
        _jvalue = new XmlSimpleList(javaResult);
        return _jvalue;
    }



    public StringEnumAbstractBase getEnumValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
                new Object[] {getPrimitiveTypeName(), "enum"}); }



    public Object getObjectValue()
        { return java_value(this); }



    public List xgetListValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "List"}); }



    public BigInteger getBigIntegerValue()
        { BigDecimal bd = bigDecimalValue(); return bd == null ? null : bd.toBigInteger(); }



    public byte[] getByteArrayValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "byte[]"}); }



    public Calendar getCalendarValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "Calendar"}); }



    public BigDecimal getBigDecimalValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
                new Object[] {getPrimitiveTypeName(), "numeric"}); }



    public List getListValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "List"}); }



    public boolean getBooleanValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "boolean"}); }



    public String getStringValue()
    {
        if (isImmutable())
        {
            if ((_flags & FLAG_NIL) != 0)
                return null;
            return compute_text(null);
        }
        // Since complex-content types don't have a "natural" string value, we
        // emit the deeply concatenated, tag-removed content of the tag.
        synchronized (monitor())
        {
            if (_isComplexContent())
                return get_store().fetch_text(TypeStore.WS_PRESERVE);

            check_dated();
            if ((_flags & FLAG_NIL) != 0)
                return null;
            return compute_text(has_store() ? get_store() : null);
        }
    }



    public Date getDateValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "Date"}); }



    public QName getQNameValue()
        { throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J,
            new Object[] {getPrimitiveTypeName(), "QName"}); }



    
	public boolean isHidden() {
		SSSlideInfoAtom slideInfo =
			(SSSlideInfoAtom)getSlideRecord().findFirstOfType(RecordTypes.SSSlideInfoAtom.typeID);
		return (slideInfo == null)
			? false
			: slideInfo.getEffectTransitionFlagByBit(SSSlideInfoAtom.HIDDEN_BIT);
	}



    private boolean shouldRemoveRow(int startRow, int endRow, int n, int rownum) {
        // is this row in the target-window where the moved rows will land?
        if (rownum >= (startRow + n) && rownum <= (endRow + n)) {
            // only remove it if the current row is not part of the data that is copied
            if (n > 0 && rownum > endRow) {
                return true;
            }
            else if (n < 0 && rownum < startRow) {
                return true;
            }
        }
        return false;
    }



    /**
     * Helper method for forbidden available call - we know the size beforehand, so it's ok ...
     *
     * @return the remaining byte until EOF
     */
    private int remainingBytes() {
        return (int)(_size - _pos);
    }



    /**
     * Helper methods for forbidden api calls
     *
     * @return the bytes remaining until the end of the stream
     */
    private int remainingBytes() {
        if (_closed) {
            throw new IllegalStateException("cannot perform requested operation on a closed stream");
        }
        return _document_size - _current_offset;
    }



	private CTPatternFill ensureCTPatternFill() {
		CTPatternFill patternFill = _fill.getPatternFill();
		if (patternFill == null) {
			patternFill = _fill.addNewPatternFill();
		}
		return patternFill;
	}



  /**
   * @return If this text piece is unicode
   */
   public boolean isUnicode()
   {
      return _usesUnicode;
   }



    /**
     * Register a hyperlink in the collection of hyperlinks on this sheet
     *
     * @param hyperlink the link to add
     */
    @Internal
    public void addHyperlink(XSSFHyperlink hyperlink) {
        hyperlinks.add(hyperlink);
    }



    /**
     * @return an iterator over the child records
     */
    
    public Iterator<EscherRecord> iterator() {
        return Collections.unmodifiableList(_childRecords).iterator();
    }



    
    public int size() {
        return currentSize.intValue();
    }



    /**
     * @return the mode of execution. true if it is executed as an Interrupt,
     *         false otherwise
     */
    public boolean inInterruptMode() {
        return this.inInterrupt;
    }



    
    protected void verifyPrecondition() throws CommandException, PreconditionException {
        if (bundleJob.getStatus() != Job.Status.PREP) {
            String msg = "Bundle " + bundleJob.getId() + " is not in PREP status. It is in : " + bundleJob.getStatus();
            LOG.info(msg);
            throw new PreconditionException(ErrorCode.E1100, msg);
        }
    }



    public boolean isSupportedApptype(String appType) {
        if (!apptypes.contains(appType.toLowerCase())) {
            return false;
        }
        return true;
    }



    public static String unEscapeHtmlTags(String string) {
        if (string != null) {
            string = string.replaceAll(HTML_LT, STRING_LT);
            string = string.replaceAll(HTML_GT, STRING_GT);
            string = string.replaceAll(HTML_CR, STRING_CR);
        }
        return string;
    }



    
    public boolean isDone() {
        return true;
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    private void writeMissingField() throws IOException {
        dos.write(missingTupleBuilder.getByteArray());
        tb.addFieldEndOffset();
    }



    public boolean isSetSemantics() {
        return setSemantics;
    }



    /**
     * Intervals with the same start and end time.
     *
     * @param ip1
     * @param ip2
     * @return boolean
     * @throws HyracksDataException
     */
    public boolean same(AIntervalPointable ip1, AIntervalPointable ip2) throws HyracksDataException {
        ip1.getStart(s1);
        ip1.getEnd(e1);
        ip2.getStart(s2);
        ip2.getEnd(e2);
        return ch.compare(ip1.getTypeTag(), ip2.getTypeTag(), s1, s2) == 0
                && ch.compare(ip1.getTypeTag(), ip2.getTypeTag(), e1, e2) == 0;
    }



    
    public void unlock(IMetadataLock.Mode mode) {
        switch (mode) {
            case WRITE:
                lock.writeLock().unlock();
                break;
            default:
                lock.readLock().unlock();
                break;
        }
    }



    
    public void lock(IMetadataLock.Mode mode) {
        switch (mode) {
            case WRITE:
                lock.writeLock().lock();
                break;
            default:
                lock.readLock().lock();
                break;
        }
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    
    public boolean isExclusiveLatchNodes() {
        return exclusiveLatchNodes;
    }



    
    public boolean isExclusiveLatchNodes() {
        return exclusiveLatchNodes;
    }



    
    public ILSMDiskComponent doFlush(ILSMIOOperation operation) throws HyracksDataException {
        throw HyracksDataException.create(ErrorCode.FLUSH_NOT_SUPPORTED_IN_EXTERNAL_INDEX);
    }



    
    protected void get(IServletRequest request, IServletResponse response) throws IOException {
        String resourcePath = request.getHttpRequest().uri();
        deliverResource(resourcePath, response);
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    
    public ILSMDiskComponent doFlush(ILSMIOOperation operation) throws HyracksDataException {
        throw new UnsupportedOperationException("flush not supported in LSM-Disk-Only-RTree");
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    private void loadDataInMemJoin() throws HyracksDataException {

        for (int pid = 0; pid < numOfPartitions; pid++) {
            if (!spilledStatus.get(pid)) {
                bufferManager.flushPartition(pid, new IFrameWriter() {
                    
                    public void open() throws HyracksDataException {

                    }

                    
                    public void nextFrame(ByteBuffer buffer) throws HyracksDataException {
                        inMemJoiner.build(buffer);
                    }

                    
                    public void fail() throws HyracksDataException {

                    }

                    
                    public void close() throws HyracksDataException {

                    }
                });
            }
        }
    }



    
    public void requestActivation() {
        requestedToBeActive = true;
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    
    public ChannelFuture lastContentFuture() {
        return future;
    }



    
    public ChannelFuture lastContentFuture() throws IOException {
        return future;
    }



    
    public boolean isExclusiveLatchNodes() {
        return false;
    }



    
    public ILSMDiskComponent doFlush(ILSMIOOperation operation) throws HyracksDataException {
        throw new UnsupportedOperationException("flush not supported in LSM-Disk-Only-BTree");
    }



    
    public boolean getLargeFlag() {
        return false;
    }



    
    public void doTransitionToWritableMode() {
        // no-op
        LOG.info("Skip transition to writable mode for readonly bookie");
    }



    void generateLedgerIdAndCreateLedger() {
        // generate a ledgerId
        final LedgerIdGenerator ledgerIdGenerator = bk.getLedgerIdGenerator();
        ledgerIdGenerator.generateLedgerId(new GenericCallback<Long>() {
            
            public void operationComplete(int rc, Long ledgerId) {
                if (BKException.Code.OK != rc) {
                    createComplete(rc, null);
                    return;
                }
                LedgerCreateOp.this.ledgerId = ledgerId;
                // create a ledger with metadata
                bk.getLedgerManager().createLedgerMetadata(ledgerId, metadata, LedgerCreateOp.this);
            }
        });
    }



    
    public void safeRun() {
        if (!isVersionCompatible()) {
            sendResponse(BookieProtocol.EBADVERSION,
                         ResponseBuilder.buildErrorResponse(BookieProtocol.EBADVERSION, request),
                         requestProcessor.readRequestStats);
            return;
        }
        processPacket();
    }



    
    public void safeRun() {
        AddResponse addResponse = getAddResponse();
        if (null != addResponse) {
            // This means there was an error and we should send this back.
            Response.Builder response = Response.newBuilder()
                    .setHeader(getHeader())
                    .setStatus(addResponse.getStatus())
                    .setAddResponse(addResponse);
            Response resp = response.build();
            sendResponse(addResponse.getStatus(), resp,
                         requestProcessor.addRequestStats);
        }
    }



	private void resolveApplicationPath() {
		if (StringUtils.hasLength(this.jersey.getApplicationPath())) {
			this.path = parseApplicationPath(this.jersey.getApplicationPath());
		}
		else {
			this.path = findApplicationPath(AnnotationUtils
					.findAnnotation(this.config.getClass(), ApplicationPath.class));
		}
	}



	public boolean getEnabled() {
		return this.enabled;
	}



	private static boolean isPackageFolder(File file) {
		return file.isDirectory() && !file.getName().startsWith(".");
	}



	@Bean
	public CaffeineCacheManager cacheManager() {
		CaffeineCacheManager cacheManager = createCacheManager();
		List<String> cacheNames = this.cacheProperties.getCacheNames();
		if (!CollectionUtils.isEmpty(cacheNames)) {
			cacheManager.setCacheNames(cacheNames);
		}
		return this.customizers.customize(cacheManager);
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	
	public void afterPropertiesSet() throws IOException {
		Properties props = new Properties();
		props.load(new ClassPathResource("application.properties").getInputStream());
		String value = props.getProperty("message");
		if (value!=null) {
			this.message = value;
		}

	}



	private boolean hasMatchingParameterTypes(Method candidate, Method current) {
		return Arrays.equals(candidate.getParameterTypes(), current.getParameterTypes());
	}



	public Boolean getEnabled() {
		return this.enabled;
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	/**
	 * Factory method to get the {@link BeanTypeRegistry} for a given {@link BeanFactory}.
	 * @param beanFactory the source bean factory
	 * @return the {@link BeanTypeRegistry} for the given bean factory
	 */
	static BeanTypeRegistry get(ListableBeanFactory beanFactory) {
		Assert.isInstanceOf(DefaultListableBeanFactory.class, beanFactory);
		DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		Assert.isTrue(listableBeanFactory.isAllowEagerClassLoading(),
				"Bean factory must allow eager class loading");
		if (!listableBeanFactory.containsLocalBean(BEAN_NAME)) {
			BeanDefinition bd = new RootBeanDefinition(BeanTypeRegistry.class);
			bd.getConstructorArgumentValues().addIndexedArgumentValue(0, beanFactory);
			listableBeanFactory.registerBeanDefinition(BEAN_NAME, bd);

		}
		return listableBeanFactory.getBean(BEAN_NAME, BeanTypeRegistry.class);
	}



	public Boolean getEnabled() {
		return this.enabled;
	}



	public Boolean getEnabled() {
		return this.enabled;
	}



	@Bean(name = "rabbitListenerContainerFactory")
	@ConditionalOnMissingBean(name = "rabbitListenerContainerFactory")
	@ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "type", havingValue = "simple", matchIfMissing = true)
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}



	@Bean
	@ConditionalOnMissingBean
	public SimpleRabbitListenerContainerFactoryConfigurer simpleRabbitListenerContainerFactoryConfigurer() {
		SimpleRabbitListenerContainerFactoryConfigurer configurer = new SimpleRabbitListenerContainerFactoryConfigurer();
		configurer.setMessageConverter(this.messageConverter.getIfUnique());
		configurer.setMessageRecoverer(this.messageRecoverer.getIfUnique());
		configurer.setRabbitProperties(this.properties);
		return configurer;
	}



	@Bean(name = "rabbitListenerContainerFactory")
	@ConditionalOnMissingBean(name = "rabbitListenerContainerFactory")
	@ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "type", havingValue = "direct")
	public DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(
			DirectRabbitListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory) {
		DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}



	@Bean
	@ConditionalOnMissingBean
	public DirectRabbitListenerContainerFactoryConfigurer directRabbitListenerContainerFactoryConfigurer() {
		DirectRabbitListenerContainerFactoryConfigurer configurer =
				new DirectRabbitListenerContainerFactoryConfigurer();
		configurer.setMessageConverter(this.messageConverter.getIfUnique());
		configurer.setMessageRecoverer(this.messageRecoverer.getIfUnique());
		configurer.setRabbitProperties(this.properties);
		return configurer;
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	
	public boolean reportException(Throwable failure) {
		FailureAnalysis analysis = analyze(failure, this.analyzers);
		return report(analysis, this.classLoader);
	}



	public Boolean getEnabled() {
		return this.enabled;
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	protected final boolean isBridgeHandlerAvailable() {
		return ClassUtils.isPresent(BRIDGE_HANDLER, getClassLoader());
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	
	public void afterPropertiesSet() throws IOException {
		Properties props = new Properties();
		props.load(new ClassPathResource("application.properties").getInputStream());
		String value = props.getProperty("message");
		if (value!=null) {
			this.message = value;
		}

	}



	public Boolean getEnabled() {
		return this.enabled;
	}



	@Bean
	@ConditionalOnMissingBean
	public Clock micrometerClock() {
		return Clock.SYSTEM;
	}



	private void handleExitCode(ConfigurableApplicationContext context,
			Throwable exception) {
		int exitCode = getExitCodeFromException(context, exception);
		if (exitCode != 0) {
			if (context != null) {
				context.publishEvent(new ExitCodeEvent(context, exitCode));
			}
			SpringBootExceptionHandler handler = getSpringBootExceptionHandler();
			if (handler != null) {
				handler.registerExitCode(exitCode);
			}
		}
	}



  public ResponseInfo __(String key, Object value) {
    items.add(Item.of(key, value, false));
    return this;
  }



  public ResponseInfo __(String key, String url, Object anchor) {
    if (url == null) {
      items.add(Item.of(key, anchor, false));
    } else {
      items.add(Item.of(key, url, anchor));
    }
    return this;
  }



  public boolean hasMinStorage(BlockInfo block) {
    return countNodes(block).liveReplicas() >= getMinStorageNum(block);
  }



  public boolean hasMinStorage(BlockInfo block, int liveNum) {
    return liveNum >= getMinStorageNum(block);
  }



  /**
   * Write the response, along with the rpc header (including verifier), to the
   * XDR.
   */
  public XDR serialize(XDR out, int xid, Verifier verifier) {
    RpcAcceptedReply reply = RpcAcceptedReply.getAcceptInstance(xid, verifier);
    reply.write(out);
    out.writeInt(this.getStatus());
    return out;
  }



  private String typeSpaceString() {
    StringBuilder sb = new StringBuilder();
    for (StorageType t : StorageType.getTypesSupportingQuota()) {
      sb.append("StorageType: " + t +
          (quota.getTypeSpace(t) < 0? "-":
          usage.getTypeSpace(t) + "/" + usage.getTypeSpace(t)));
    }
    return sb.toString();
  }



  /**
   * Set block keys, only to be used in slave mode
   */
  public synchronized void addKeys(ExportedBlockKeys exportedKeys)
      throws IOException {
    if (isMaster || exportedKeys == null)
      return;
    LOG.info("Setting block keys");
    removeExpiredKeys();
    this.currentKey = exportedKeys.getCurrentKey();
    BlockKey[] receivedKeys = exportedKeys.getAllKeys();
    for (int i = 0; i < receivedKeys.length; i++) {
      if (receivedKeys[i] == null)
        continue;
      this.allKeys.put(receivedKeys[i].getKeyId(), receivedKeys[i]);
    }
  }



  
  public boolean hasSuccessfullyUnregistered() {
    // bogus - Not Required
    return true;
  }



  void snapshot(MetricsRecordBuilder rb, boolean all) {
    registry.snapshot(rb, all);
  }



  public String getHost() {
    return this.host;
  }



  public String getPath() {
    return this.path;
  }



  
  public boolean isExclusive() {
    NodeLabelProtoOrBuilder p = viaProto ? proto : builder;
    return p.getIsExclusive();
  }



  /**
   * Set total resources on the node.
   * @param resource Total resources on the node.
   */
  public synchronized void updateTotalResource(Resource resource){
    this.totalResource = resource;
    this.unallocatedResource = Resources.subtract(totalResource,
        this.allocatedResource);
  }



  protected boolean keepTaskFiles(JobConf conf) {
    return (conf.getKeepTaskFilesPattern() != null || conf
        .getKeepFailedTaskFiles());
  }



  
  public void setServiceData(final Map<String, ByteBuffer> serviceData) {
    if (serviceData == null)
      return;
    initServiceData();
    this.serviceData.putAll(serviceData);
  }



  
  protected void setFieldsFromProperties(Properties props, StorageDirectory sd)
      throws IOException {
    setLayoutVersion(props, sd);
    setNamespaceID(props, sd);
    setcTime(props, sd);
    
    String sbpid = props.getProperty("blockpoolID");
    setBlockPoolID(sd.getRoot(), sbpid);
  }



  /**
   * Run the list object call without any failure probability.
   * This stops a very aggressive failure rate from completely overloading
   * the retry logic.
   * @param listObjectsRequest request
   * @return listing
   * @throws AmazonClientException failure
   */
  private ObjectListing innerlistObjects(ListObjectsRequest listObjectsRequest)
      throws AmazonClientException, AmazonServiceException {
    LOG.debug("prefix {}", listObjectsRequest.getPrefix());
    ObjectListing listing = super.listObjects(listObjectsRequest);
    listing = filterListObjects(listing);
    listing = restoreListObjects(listObjectsRequest, listing);
    return listing;
  }



  private static boolean isSuccess(Code code) {
    return (code == Code.OK);
  }



  private static boolean isNodeDoesNotExist(Code code) {
    return (code == Code.NONODE);
  }



  private static boolean isNodeExists(Code code) {
    return (code == Code.NODEEXISTS);
  }



  
  public BatchedEntries<CachePoolEntry> listCachePools(String prevKey)
      throws IOException {
    try {
      return new BatchedCachePoolEntries(
        rpcProxy.listCachePools(null,
          ListCachePoolsRequestProto.newBuilder().
            setPrevPoolName(prevKey).build()));
    } catch (ServiceException e) {
      throw ProtobufHelper.getRemoteException(e);
    }
  }



  /**
   * Does this Filesystem have a metadata store?
   * @return true if the FS has been instantiated with a metadata store
   */
  public boolean hasMetadataStore() {
    return !S3Guard.isNullMetadataStore(metadataStore);
  }



    private static boolean builtIn(final String manager) {
        return manager == null || manager.isEmpty()
                    || "openejb".equalsIgnoreCase(manager)
                    || "tomee".equalsIgnoreCase(manager);
    }



    @SuppressWarnings("rawtypes")
    static void addAcceptedTweetIDs(List<String> openEJBStatusIDs,
                                    Map keyValue, String tweet) {
        logger.info("Adding Tweet:" + tweet);
        Number tweetId = (Number) keyValue.get("id");
        openEJBStatusIDs.add(tweetId.toString());
    }



    public static void exit(Object oldContext) {
        if (oldContext != null && !(oldContext instanceof WebBeansContext)) throw new IllegalArgumentException("ThreadSingletonServiceImpl can only be used with WebBeansContext, not " + oldContext.getClass().getName());
        contexts.set((WebBeansContext) oldContext);
    }



    public boolean isEnableMtom() {
        return enableMtom != null && enableMtom;
    }



    private DataSource getTargetDataSource() {
        return getDelegate().getDataSource();
    }



    public static String highestVersion(final String info, final String prefix, final String defaultVersion) {
        final VersionRangeResult result = VersionResolver.versions(info, defaultVersion);
        if (result == null) {
            return defaultVersion;
        }
        final List<Version> versions = result.getVersions();
        Collections.sort(versions); // Version impl comparable so we just need to call it :)
        Version usedVersion = null;
        for (Version current : versions) {
            if (current.toString().startsWith(prefix)) {
                usedVersion = current;
            }
        }
        if (usedVersion != null) {
            return usedVersion.toString();
        }
        return defaultVersion;
    }



    private void execute(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        JsonExecutor.execute(req, resp, new JsonExecutor.Executor() {
            
            public void call(Map<String, Object> json) throws Exception {
                final CommandExecutor executor = new CommandExecutor();
                final Map<String, Object> result = executor.execute(req.getParameter("strParam"));
                json.putAll(result);
            }
        });
    }



    
    protected boolean useCovariance() {
        return true;
    }



    
    protected boolean useCovariance() {
        return true;
    }



    
    protected boolean useCovariance() {
        return true;
    }



    
    public int read(long filePos, @Nonnull ByteBuffer buf) throws IOException {
        return NIOUtils.read(channel, buf, filePos);
    }



    
    public int write(final long filePos, @Nonnull final ByteBuffer buf) throws IOException {
        return NIOUtils.writeFully(channel, buf, filePos);
    }



    @Nonnull
    public byte[] serialize(boolean compress) throws HiveException {
        try {
            if (compress) {
                return ObjectUtils.toCompressedBytes(_root);
            } else {
                return ObjectUtils.toBytes(_root);
            }
        } catch (IOException ioe) {
            throw new HiveException("IOException cause while serializing DecisionTree object", ioe);
        } catch (Exception e) {
            throw new HiveException("Exception cause while serializing DecisionTree object", e);
        }
    }



    @Nonnull
    public byte[] serialize(boolean compress) throws HiveException {
        try {
            if (compress) {
                return ObjectUtils.toCompressedBytes(_root);
            } else {
                return ObjectUtils.toBytes(_root);
            }
        } catch (IOException ioe) {
            throw new HiveException("IOException cause while serializing DecisionTree object", ioe);
        } catch (Exception e) {
            throw new HiveException("Exception cause while serializing DecisionTree object", e);
        }
    }



    
    protected boolean useCovariance() {
        return true;
    }



    
    protected boolean useCovariance() {
        return true;
    }



    protected boolean useCovariance() {
        return false;
    }



    
    protected boolean useCovariance() {
        return true;
    }



    
    protected boolean useCovariance() {
        return true;
    }



    private static void doSystemUninstall()
    {
        if ( JANSI )
        {
            AnsiConsole.systemUninstall();
        }
    }



    /**
     * Create a logger level renderer.
     * @return a logger level renderer
     * @since 3.2.0
     */
    @SuppressWarnings( "checkstyle:magicnumber" )
    public static LoggerLevelRenderer level()
    {
        return JANSI ? new AnsiMessageBuilder( 20 ) : new PlainMessageBuilder( 7 );
    }



	private void assertNotExecuted() {
		Assert.state(!executed.get(), "This Cassandra Batch was already executed");
	}



	/**
	 * Include the {@code columnName} as {@link Selector}. Including a column name overrides the exclusion if the column
	 * was already excluded.
	 *
	 * @param columnName must not be {@literal null}.
	 * @return a new {@link Columns} object containing all column definitions and the selected {@code columnName}.
	 */
	public Columns select(CqlIdentifier columnName, Selector selector) {

		Assert.notNull(columnName, "Column name must not be null");

		Map<ColumnName, Selector> result = new LinkedHashMap<>(this.columns);
		result.put(ColumnName.from(columnName), selector);

		return new Columns(result);
	}



	/**
	 * Include the {@code columnName} as {@link Selector}. Including a column name overrides the exclusion if the column
	 * was already excluded.
	 *
	 * @param columnName must not be {@literal null}.
	 * @return a new {@link Columns} object containing all column definitions and the selected {@code columnName}.
	 */
	public Columns select(String columnName, Selector selector) {

		Assert.notNull(columnName, "Column name must not be null");

		Map<ColumnName, Selector> result = new LinkedHashMap<>(this.columns);
		result.put(ColumnName.from(columnName), selector);

		return new Columns(result);
	}



	private BeanDefinitionHolder registerDefaultContext(BeanDefinitionRegistry registry) {

		BeanDefinitionHolder contextBean = new BeanDefinitionHolder(
				BeanDefinitionBuilder.genericBeanDefinition(BasicCassandraMappingContext.class).getBeanDefinition(),
				DefaultBeanNames.CONTEXT);

		registry.registerBeanDefinition(contextBean.getBeanName(), contextBean.getBeanDefinition());

		return contextBean;
	}



	boolean callbacksResolved() {
		return !_containerState.referenceCallbacks().values().stream().flatMap(
			valueMap -> valueMap.values().stream()
		).filter(
			c -> !c.resolved()
		).findFirst().isPresent();
	}



  /**
   * Look for a BluepintContainer service in a given bundle
   * @param b Bundle to look in
   * @return BlueprintContainer service, or null if none available
   */
  private static ServiceReference findBPCRef (Bundle b) 
  {
    ServiceReference[] refs = b.getRegisteredServices();
    ServiceReference result = null;
    if (refs != null) {
      outer: for (ServiceReference r : refs) {
        String[] objectClasses = (String[]) r.getProperty(Constants.OBJECTCLASS);
        for (String objectClass : objectClasses) {
          if (objectClass.equals(BlueprintContainer.class.getName())) {
            // Arguably we could put an r.isAssignableTo(jndi-url-bundle, BlueprintContainer.class.getName())
            // check here. But if you've got multiple, class-space inconsistent instances of blueprint in 
            // your environment, you've almost certainly got other problems.
            result = r;
            break outer;
          }
        }
      }
    }
    return result;
  }



	
	public ServiceScope scope() {
		return _scope;
	}



	public static <A, B, C> OSGi<C> combine(Function2<A, B, C> fun, OSGi<A> a, OSGi<B> b) {
		return b.applyTo(a.applyTo(just(fun.curried())));
	}



  public DeploymentMetadata parseDeploymentMetadata(InputStream in) throws IOException
  {
    return createDeploymentMetadata(ManifestProcessor.parseManifest(in));
  }



    /**
     * This method is used to create a URL Context. It does this by looking for
     * the URL context's ObjectFactory in the service registry.
     * 
     * @param context
     * @param urlScheme
     * @param env
     * @return a Context
     * @throws NamingException
     */
    public static ContextProvider createURLContext(final BundleContext context,
                                           final String urlScheme, 
                                           final Hashtable<?, ?> env)
        throws NamingException {
      
        ServicePair<ObjectFactory> urlObjectFactory = getURLObjectFactory(context, urlScheme, env);
        
        if (urlObjectFactory != null) {
            ObjectFactory factory = urlObjectFactory.get();
            
            if (factory != null) {
                return new URLContextProvider(context, urlObjectFactory.getReference(), factory, env);
            }
        }

        // if we got here then we couldn't find a URL context factory so return null.
        return null;
    }



	/**
	 * Convert given Query into a SolrQuery executable via {@link org.apache.solr.client.solrj.SolrServer}
	 * 
	 * @param query
	 * @return
	 */
	
	public final SolrQuery doConstructSolrQuery(SolrDataQuery query) {
		Assert.notNull(query, "Cannot construct solrQuery from null value.");
		Assert.notNull(query.getCriteria(), "Query has to have a criteria.");

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setParam(CommonParams.Q, getQueryString(query));
		if (query instanceof Query) {
			processQueryOptions(solrQuery, (Query) query);
		}
		if (query instanceof FacetQuery) {
			processFacetOptions(solrQuery, (FacetQuery) query);
		}
		if (query instanceof HighlightQuery) {
			processHighlightOptions(solrQuery, (HighlightQuery) query);
		}
		return solrQuery;
	}



	@Column(name = "deleted")
	public boolean getDeleted() {
		return deleted;
	}



	protected void readConfig(Properties properties) throws ConfigurationException {
		uddiNodes = readNodeConfig(config, properties);
		uddiClerks = readClerkConfig(config, uddiNodes);
		xServiceBindingRegistrations = readXServiceBindingRegConfig(config,uddiClerks);
		xBusinessRegistrations = readXBusinessRegConfig(config, uddiClerks);
	}



    private synchronized void shutdown() {
        if (ep != null && !ep.isPublished()) {
            log.fatal("Hey, someone should tell the developer to call SubscriptionCallbackListern.stop(...) before ending the program. Stopping endpoint at " + callback);
            unregisterAllCallbacks();
            ep.stop();
            ep = null;
            callback = null;
        }

    }



        /**
         * client.clerks.clerk(" + i + ")[@isPasswordEncrypted]
         *
         * @return
         */
        public boolean getIsPasswordEncrypted() {
                return this.isencrypted;

        }



  
  public String getSegmentValue(){
    return "$value";
  }



  
  public String getSegmentValue(final boolean includeFilters){
    return actionImport == null ? (action == null ? "" : action.getName()) : actionImport.getName();
  }



  
  public String getSegmentValue(){
    return variableText;
  }



  
  public String getSegmentValue(){
    return property.getName();
  }



  private void assertIsNullNode(String key, JsonNode jsonNode) throws DeserializerException {
    if (jsonNode.isNull()) {
      throw new DeserializerException("Annotation: " + key + "must not have a null value.",
          DeserializerException.MessageKeys.INVALID_NULL_ANNOTATION, key);
    }
  }



  public Object readAdditionalProperty(final String name) {
    return getPropertyValue(name, null);
  }



    public static Languages getInstance(NameType nameType) {
        return LANGUAGES.get(nameType);
    }



    public boolean matchesAllInstancesOf(SequenceType sTypeArg, SequenceType sTypeOutput) {
        if (sTypeOutput != null && sTypeOutput.getItemType().isAtomicType()) {
            return true;
        }
        return false;
    }



    
    public boolean matchesAllInstancesOf(SequenceType sTypeArg, SequenceType sTypeOutput) {
        if (sTypeArg != null && sTypeOutput != null && sTypeOutput.equals(sTypeArg)) {
            // Same type.
            return true;
        }
        return false;
    }



    private Object baseRemoveProperty(final String key) {
        return map.remove(prefixedKey(key));
    }



    public void defer(final Runnable runnable) {
        _scheduler.registerSynchronizer(new Scheduler.Synchronizer() {
            public void afterCompletion(boolean success) {
            }
            public void beforeCompletion() {
                runnable.run();
            }
        });
    }



	
	public Object visitArray(Object old) {
		throw new UnsupportedOperationException("Create new Array is unsupported");
	}



  public void run() {
    final ActivityInfo child = new  ActivityInfo(genMonotonic(),
            _remaining.get(0),
            newChannel(TerminationChannel.class), newChannel(ParentScopeChannel.class));
    instance(createChild(child, _scopeFrame, _linkFrame));
    instance(new ACTIVE(child));
  }



    public boolean isMaster() {
        return isMaster;
    }



  public final void run() {
    getBpelRuntimeContext().terminate();
    _self.parent.completed(null, CompensationHandler.emptySet());
  }



  /** The string "{0}" is not a valid XPath 1.0 expression. */
  public CompilationMessage warnXPath20Syntax(String xPathString, String message) {
    return super.formatCompilationMessage(
        "The string \"{0}\" is not a valid XPath 2.0 expression: {1}", xPathString, message);
  }



    private static char nextChar( String input, int i )
    {
        return input.length() > i + 1 ? input.charAt( i + 1 ) : '\0';
    }



    /** {@inheritDoc} */
    protected void init()
    {
        hasTitle = false;
        authorDateFlag = false;
        verbatimFlag = false;
        externalLinkFlag = false;
        graphicsFileName = null;
        tableHasCaption = false;
        savedOut = null;
        tableRows = null;
        tableHasGrid = false;
    }



  
  public void flush(int operatorId, long windowId) throws IOException
  {
    // Checkpoint already present in HDFS during save, when syncCheckpoint is true.
    if (isSyncCheckpoint()) {
      return;
    }
    copyToHDFS(operatorId, windowId);
  }



  public File resourcesDirectory()
  {
    return resourcesDirectory;
  }



    /**
     * Calc processing stats
     */
    public boolean doStatistics() {
        return doProcessingStats;
    }



    public AbstractRxTask createRxTask() {
        return getReplicationThread();
    }



    public void setEventSubType(EventSubType eventSubType) {
        this.eventSubType = eventSubType;
    }



    public boolean doDomainReplication() {
        return false;
    }



    public void heartbeat() {
        if ( clusterSender!=null ) clusterSender.heartbeat();
        super.heartbeat();
    }



    public int incSequence() {
        return sequence++;
    }



    public AbstractRxTask createRxTask() {
        NioReplicationTask thread = new NioReplicationTask(this,this);
        thread.setUseBufferPool(this.getUseBufferPool());
        thread.setRxBufSize(getRxBufSize());
        thread.setOptions(getWorkerThreadOptions());
        return thread;
    }



    public void remove(Member member) {
        //disconnect senders
        NioSender sender = (NioSender)nioSenders.remove(member);
        if ( sender != null ) sender.disconnect();
    }



    /**
     * @return Returns the sendClusterDomainOnly.
     */
    public boolean doDomainReplication() {
        return sendClusterDomainOnly;
    }



    public void remove(Member member) {
        //disconnect senders
        BioSender sender = (BioSender)bioSenders.remove(member);
        if ( sender != null ) sender.disconnect();
    }



    public boolean doDomainReplication() {
        return sendClusterDomainOnly;
    }



	
	public Query doCreateQuery(Object[] values) {

		return query.createQuery(values);
	}



	@RequestMapping(method=RequestMethod.GET)
	public List<AppSummary> list(Account account) {
		return connectedAppRepository.findAppSummaries(account.getId());
	}



	@RequestMapping(value="/new", method=RequestMethod.GET)
	public AppForm newForm() {
		return connectedAppRepository.getNewAppForm();
	}



	public StringTemplate loadStringTemplate(Resource resource) {
		ResourceStringTemplateFactory factory = templateFactories.get(resource);
		if (factory == null) {
			factory = new ResourceStringTemplateFactory(resource);
			templateFactories.put(resource, factory);			
		}
		return factory.getStringTemplate();
	}



	public StringTemplate getStringTemplate() {
		return new DelegatingStringTemplate(compiledPrototype.getInstanceOf());
	}



	public String toString() {
		return id.toString();
	}



	public Invite findInvite(String token) throws NoSuchInviteException, InviteAlreadyAcceptedException {
		Invite invite = queryForInvite(token);
		if (invite.isAccepted()) {
			throw new InviteAlreadyAcceptedException(token);
		}
		return invite;
	}



	public Validator getValidator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/validation");
		if (environment.acceptsProfiles("embedded")) {
			messageSource.setCacheSeconds(0);
		}
		factory.setValidationMessageSource(messageSource);
		return factory;
	}



	public RedisSession findById(String id) {
		return getSession(id, false);
	}



  /**
   * Gets all the namespaces from ZK. The first arg (t) the BiConsumer accepts is the ID and the second (u) is the namespaceName.
   */
  private static void getAllNamespaces(Instance instance, BiConsumer<String,String> biConsumer) {
    final ZooCache zc = getZooCache(instance);
    List<String> namespaceIds = zc.getChildren(ZooUtil.getRoot(instance) + Constants.ZNAMESPACES);
    for (String id : namespaceIds) {
      byte[] path = zc.get(ZooUtil.getRoot(instance) + Constants.ZNAMESPACES + "/" + id + Constants.ZNAMESPACE_NAME);
      if (path != null) {
        biConsumer.accept(id, new String(path, UTF_8));
      }
    }
  }



  /**
   * Moves a file to trash. If this garbage collector is not using trash, this method returns false and leaves the file alone. If the file is missing, this
   * method returns false as opposed to throwing an exception.
   *
   * @return true if the file was moved to trash
   * @throws IOException
   *           if the volume manager encountered a problem
   */
  boolean archiveOrMoveToTrash(Path path) throws IOException {
    if (shouldArchiveFiles()) {
      return archiveFile(path);
    } else {
      if (!isUsingTrash())
        return false;
      try {
        return fs.moveToTrash(path);
      } catch (FileNotFoundException ex) {
        return false;
      }
    }
  }



    public void onClose() throws IOException {
        if (!statusCodeSet) {
            getHttpServletResponse().setStatus(HttpTransportConstants.STATUS_ACCEPTED);
        }
    }



    public void onClose() throws IOException {
        MailTransportUtils.closeFolder(folder, deleteAfterReceive);
        MailTransportUtils.closeService(store);
    }



    public void onClose() throws IOException {
        socket.close();
    }



    public void onClose() {
        connection.disconnect();
    }



    protected void onClose() throws IOException {
        JmsUtils.closeSession(session);
        ConnectionFactoryUtils.releaseConnection(connection, connectionFactory, true);
    }



    public void onClose() throws IOException {
        socket.close();
    }



    private String generateTimestampSUID() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }



    /**
     * Appends the SubCode to the SubCode list.
     * 
     * @param subCode The SubCode element as detailed by the SOAP 1.2 spec. 
     */
    public void addSubCode(QName subCode) {
        if (subCodes == null) {
            subCodes = new LinkedList<QName>();
        }
        subCodes.add(subCode);
    }



    public String setSignatureBytes(byte[] signatureOctets) {
        setEncodedSignature(Base64UrlUtility.encode(signatureOctets));
        return getSignedEncodedJws();
    }



    public String setSignatureText(String signatureText) {
        setEncodedSignature(Base64UrlUtility.encode(signatureText));
        return getSignedEncodedJws();
    }



    
    public Client doGetClient(final String clientId) throws OAuthServiceException {
        return execute(new EntityManagerOperation<Client>() {
            
            public Client execute(EntityManager em) {
                return em.find(Client.class, clientId);
            }
        });
    }



    protected String resolveXMLResourceURI(String path) {
        MessageContext mc = getContext();
        if (mc != null) {
            String httpBasePath = (String)mc.get("http.base.path");
            UriBuilder builder = null;
            if (httpBasePath != null) {
                builder = UriBuilder.fromPath(httpBasePath);
            } else {
                builder = mc.getUriInfo().getBaseUriBuilder();
            }
            return builder.path(path).path(xmlResourceOffset).build().toString();
        } else {
            return path; 
        }
    }



    
    public Client doGetClient(String clientId) throws OAuthServiceException {
        return clientCache.get(clientId);
    }



    protected String buildErrorMessage(ConstraintViolation<?> violation) {
        return "Value " 
            + (violation.getInvalidValue() != null ? "'" + violation.getInvalidValue().toString() + "'" : "(null)")
            + " of " + violation.getRootBeanClass().getSimpleName()
            + "." + violation.getPropertyPath()
            + ": " + violation.getMessage();
    }



    
    public Client doGetClient(String clientId) throws OAuthServiceException {
        return ModelEncryptionSupport.decryptClient(clientsMap.get(clientId), key);
    }



    
    public Client doGetClient(String clientId) throws OAuthServiceException {
        return getCacheValue(clientCache, clientId, Client.class);
    }



    @Bean
    @ConditionalOnMissingBean(name = "cxfServletRegistration")
    public ServletRegistrationBean cxfServletRegistration() {
        String path = this.properties.getPath();
        String urlMapping = path.endsWith("/") ? path + "*" : path + "/*";
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new CXFServlet(), urlMapping);
        CxfProperties.Servlet servletProperties = this.properties.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        for (Map.Entry<String, String> entry : servletProperties.getInit().entrySet()) {
            registration.addInitParameter(entry.getKey(), entry.getValue());
        }
        return registration;
    }



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



    /**
     * Read from the input stream and write to the output. This method
     * does not close any stream; calling methods must take care of that.
     * @throws IOException when io error happens
     */
    static void transformStream(InputStream in, OutputStream out) throws IOException {
        StreamHelper s = new StreamHelper(in, out);
        s.stream();
    }



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>double</code> value of the attribute
	 */
	public double doubleValue(){
		String value = super.getValue();
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against NonNegativeInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
        	//2DO: need validate value against PositiveInteger
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>int</code> value of the attribute
	 */
	public int intValue(){
		String value = super.getValue();
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



	/**
	 * @return Returns the <code>boolean</code> value of the attribute
	 */
	public boolean booleanValue(){
		String value = super.getValue();
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			// TODO: validation handling/logging
			throw (e);
		}
	}



    protected void doActionJob(final JobKey jobKey, final JobAction action) {
        try {
            if (scheduler.getScheduler().checkExists(jobKey)) {
                switch (action) {
                    case START:
                        scheduler.getScheduler().triggerJob(jobKey);
                        break;

                    case STOP:
                        scheduler.getScheduler().interrupt(jobKey);
                        break;

                    default:
                }
            } else {
                LOG.warn("Could not find job {}", jobKey);
            }
        } catch (SchedulerException e) {
            LOG.debug("Problems during {} operation on job {}", action.toString(), jobKey, e);
        }
    }



    protected AnyUtils anyUtils() {
        synchronized (this) {
            if (anyUtils == null) {
                anyUtils = init();
            }
        }
        return anyUtils;
    }



    private static Name getName(final String evalConnObjectLink, final String connObjectKey) {
        // If connObjectLink evaluates to an empty string, just use the provided connObjectKey as Name(),
        // otherwise evaluated connObjectLink expression is taken as Name().
        Name name;
        if (StringUtils.isBlank(evalConnObjectLink)) {
            // add connObjectKey as __NAME__ attribute ...
            LOG.debug("Add connObjectKey [{}] as __NAME__", connObjectKey);
            name = new Name(connObjectKey);
        } else {
            LOG.debug("Add connObjectLink [{}] as __NAME__", evalConnObjectLink);
            name = new Name(evalConnObjectLink);

            // connObjectKey not propagated: it will be used to set the value for __UID__ attribute
            LOG.debug("connObjectKey will be used just as __UID__ attribute");
        }

        return name;
    }



    
    public List<ReportTemplateTO> listTemplates() {
        return getService(ReportTemplateService.class).list();
    }



    public Boolean isShow() {
        return show;
    }



    public List<NotificationTO> list() {
        return getService(NotificationService.class).list();
    }



    
    public List<MailTemplateTO> listTemplates() {
        return getService(MailTemplateService.class).list();
    }



    /**
     * Helper method to invoke logging per provisioning result, for the given trace level.
     *
     * @param results provisioning results
     * @param level trace level
     * @return report as string
     */
    public static String generate(final Collection<ProvisioningReport> results, final TraceLevel level) {
        StringBuilder sb = new StringBuilder();
        for (ProvisioningReport result : results) {
            sb.append(result.getReportString(level)).append('\n');
        }
        return sb.toString();
    }



    private ResourceOperation toResourceOperation(final MatchingRule rule) {
        switch (rule) {
            case UPDATE:
                return ResourceOperation.UPDATE;
            case DEPROVISION:
            case UNASSIGN:
                return ResourceOperation.DELETE;
            default:
                return ResourceOperation.NONE;
        }
    }



    private ResourceOperation toResourceOperation(final UnmatchingRule rule) {
        switch (rule) {
            case ASSIGN:
            case PROVISION:
                return ResourceOperation.CREATE;
            default:
                return ResourceOperation.NONE;
        }
    }



    public void numberFormatException(final String what, final String key) {
        Messages.printIdNotNumberDeletedMessage(what, key);
    }



    private void doHandle(
            final List<? extends Any<?>> anys,
            final SyncopePushResultHandler handler,
            final ExternalResource resource)
            throws JobExecutionException {

        for (Any<?> any : anys) {
            try {
                handler.handle(any.getKey());
            } catch (Exception e) {
                LOG.warn("Failure pushing '{}' on '{}'", any, resource, e);
                throw new JobExecutionException("While pushing " + any + " on " + resource, e);
            }
        }
    }



    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public Pair<String, UserTO> selfRead() {
        return ImmutablePair.of(
                POJOHelper.serialize(AuthContextUtils.getAuthorizations()),
                binder.returnUserTO(binder.getAuthenticatedUserTO()));
    }



  /**
   * Delete a component
   * @param componentName component name
   * @throws IOException
   */
  public void deleteComponent(String componentName) throws IOException {
    String path = RegistryUtils.componentPath(
        user, sliderServiceclass, instanceName,
        componentName);
    registryOperations.delete(path, false);
  }



  /**
   * Get the local JVM classpath split up
   * @return the list of entries on the JVM classpath env var
   */
  public Collection<String> localJVMClasspath() {
    return splitClasspath(System.getProperty("java.class.path"));
  }



  /**
   * Create a single thread executor using this naming policy
   * @param name base thread name
   * @param daemons flag to indicate the threads should be marked as daemons
   * @return an executor
   */
  public static ExecutorService singleThreadExecutor(String name,
      boolean daemons) {
    return Executors.newSingleThreadExecutor(
        new ServiceThreadFactory(name, daemons));
  }



  private String ifdef(String arg, String val) {
    if (is(val)) {
      return arg + " " + val + " ";
    } else {
      return "";
    }
  }



  private String ifdef(String arg, boolean val) {
    return val ? (arg + " "): "";
  }



  /**
   * Update a persisted instance definition
   * @param coreFS filesystem
   * @param dir directory to load from
   * @param instanceDefinition instance definition to save do
   * @throws SliderException
   * @throws IOException
   * @throws LockAcquireFailedException
   */
  public static void saveInstanceDefinition(CoreFileSystem coreFS,
      Path dir,
      AggregateConf instanceDefinition)
      throws SliderException, IOException, LockAcquireFailedException {
    ConfPersister persister =
      new ConfPersister(coreFS, dir);
    persister.save(instanceDefinition, null);
  }



  /**
   * Add an entry to the environment
   * @param envVar envVar -must not be null
   * @param val value 
   */
  public void setEnv(String envVar, String val) {
    Preconditions.checkArgument(envVar != null, "envVar");
    Preconditions.checkArgument(val != null, "val");
    processBuilder.environment().put(envVar, val);
  }



  
  protected void doRun() throws Exception {
    runnable.run();
  }



  /**
   * Returns a new path by appending an extension.
   *
   * @param extractFrom the path to extract the extension from
   * @param appendTo the path to append the extension to
   */
  public static String addExtension(String extractFrom, String appendTo) {
    String suffix = getExtension(extractFrom);
    if (!suffix.isEmpty()) {
      return appendTo + '.' + suffix;
    }
    return appendTo;
  }



    
    protected String getValue() throws XMLStreamException {
        return reader.getElementText();
    }



    
    public void doReduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long length = 0;
        for (LongWritable v : values) {
            length += v.get();
        }

        outputKey.set(length);
        context.write(key, outputKey);
    }



    public void validateArgs(String... args) {
        for (String arg : args) {
            Preconditions.checkState(!StringUtils.isEmpty(arg));
        }
    }



    
    public void doMap(Text key, Text value, Context context) throws IOException, InterruptedException {
        lastKey = key;

        int bytesLength = key.getLength() + value.getLength();
        bytesRead += bytesLength;

        if (bytesRead >= ONE_MEGA_BYTES) {
            outputValue.set(bytesRead);
            context.write(key, outputValue);

            // reset bytesRead
            bytesRead = 0;
        }

    }



    public void exportToFile(File file) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            getAllProperties().store(fos, file.getAbsolutePath());
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }



    public static String concatResourcePath(String descName) {
        return ResourceStore.CUBE_DESC_RESOURCE_ROOT + "/" + descName + MetadataConstants.FILE_SURFIX;
    }



    @RequestMapping(value = "/{filter}/{project}", method = { RequestMethod.DELETE })
    @ResponseBody
    public Map<String, String> removeFilter(@PathVariable String filter, @PathVariable String project) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        extFilterService.removeExtFilterFromProject(filter, project);
        extFilterService.removeExternalFilter(filter);
        result.put("success", "true");
        return result;
    }



    public Properties extractKafkaConfigToProperties() {
        Properties prop = new Properties();
        prop.putAll(this.properties);
        return prop;
    }



    
    protected void doSetup(Context context) throws IOException {
        super.bindCurrentConfiguration(context.getConfiguration());
    }



    
    public void doReduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        for (LongWritable v : values) {
            bytesRead += v.get();
        }

        if (bytesRead >= ONE_GIGA_BYTES) {
            gbPoints.add(new Text(key));
            bytesRead = 0; // reset bytesRead
        }
    }



    
    public boolean contains(Dictionary<?> other) {
        return this.equals(other);
    }



    
    protected void doSetup(Context context) throws IOException {
        super.bindCurrentConfiguration(context.getConfiguration());
    }



    
    protected void doSetup(Context context) throws IOException {
        Configuration conf = context.getConfiguration();
        bindCurrentConfiguration(conf);
    }



    
    protected void doSetup(Context context) throws IOException {
        super.bindCurrentConfiguration(context.getConfiguration());
    }



    
    protected void doSetup(Context context) throws IOException {
        super.bindCurrentConfiguration(context.getConfiguration());
    }



    public static String concatResourcePath(String streamingName) {
        return ResourceStore.KAFKA_RESOURCE_ROOT + "/" + streamingName + MetadataConstants.FILE_SURFIX;
    }



    private String toUrlString(URLFileName name)
    {
        return toUrlString(name, true);
    }



    /**
     * Returns an Iterable instance over expression path components based on this entity.
     * 
     * @since 3.0
     */
    
    @SuppressWarnings("unchecked")
    public Iterable<PathComponent<DbAttribute, DbRelationship>> resolvePath(
            final Expression pathExp,
            final Map aliasMap) {

        if (pathExp.getType() == Expression.DB_PATH) {

            return new Iterable<PathComponent<DbAttribute, DbRelationship>>() {

                public Iterator iterator() {
                    return new PathComponentIterator(DbEntity.this, (String) pathExp
                            .getOperand(0), aliasMap);
                }
            };
        }

        throw new ExpressionException("Invalid expression type: '"
                + pathExp.expName()
                + "',  DB_PATH is expected.");
    }



    protected boolean visitNode(EJBQLExpressionVisitor visitor) {
        return visitor.visitSelect(this);
    }



	/**
	 * Merges prefetch into the query prefetch tree.
	 * 
	 * @return this object
	 */
	public SelectById<T> prefetch(PrefetchTreeNode prefetch) {

		if (prefetch == null) {
			return this;
		}

		if (prefetches == null) {
			prefetches = new PrefetchTreeNode();
		}

		prefetches.merge(prefetch);
		return this;
	}



	/**
	 * Merges a prefetch path with specified semantics into the query prefetch
	 * tree.
	 * 
	 * @return this object
	 */
	public SelectById<T> prefetch(String path, int semantics) {

		if (path == null) {
			return this;
		}

		if (prefetches == null) {
			prefetches = new PrefetchTreeNode();
		}

		prefetches.addPath(path).setSemantics(semantics);
		return this;
	}



    /**
     * Adds one or more aliases for the qualifier expression path. Aliases serve to
     * instruct Cayenne to generate separate sets of joins for overlapping paths, that
     * maybe needed for complex conditions. An example of an <i>implicit<i> splits is
     * this method: {@link ExpressionFactory#matchAllExp(String, Object...)}.
     * 
     * @since 3.0
     */
    public void aliasPathSplits(String path, String... aliases) {
        metaData.addPathSplitAliases(path, aliases);
    }



	
	public void close() {

		// swap the underlying DataSource to prevent further interaction with
		// the callers
		this.dataSource = new StoppedDataSource(dataSource);

		// shut down the thread..
		this.dataSourceManager.shutdown();
	}



    /**
     * Recalculates whether a relationship is toMany or toOne, based on the underlying db
     * relationships.
     */
    public void recalculateToManyValue() {
        // If there is a single toMany along the path, then the flattend
        // rel is toMany. If all are toOne, then the rel is toOne.
        // Simple (non-flattened) relationships form the degenerate case
        // taking the value of the single underlying dbrel.
        for (DbRelationship thisRel : this.dbRelationships) {
            if (thisRel.isToMany()) {
                this.toMany = true;
                return;
            }
        }

        this.toMany = false;
    }



  final public void numericExpression() throws ParseException {
    bitwiseOr();
  }



    /**
     * Returns an Iterable instance over expression path components based on this entity.
     * 
     * @since 3.0
     */
    
    @SuppressWarnings("unchecked")
    public Iterable<PathComponent<ObjAttribute, ObjRelationship>> resolvePath(
            final Expression pathExp,
            final Map aliasMap) {

        if (pathExp.getType() == Expression.OBJ_PATH) {

            return new Iterable<PathComponent<ObjAttribute, ObjRelationship>>() {

                public Iterator iterator() {
                    return new PathComponentIterator(ObjEntity.this, (String) pathExp
                            .getOperand(0), aliasMap);
                }
            };
        }

        throw new ExpressionException("Invalid expression type: '"
                + pathExp.expName()
                + "',  OBJ_PATH is expected.");
    }



    /**
     * Return filter for columns in case we should take this table
     *
     * @param tableName
     * @return
     */
    public PatternFilter getIncludeTableColumnFilter(String tableName) {
        IncludeTableFilter include = null;
        for (IncludeTableFilter p : includes) {
            if (p.pattern == null || p.pattern.matcher(tableName).matches()) {
                include = p;
                break;
            }
        }

        if (include == null) {
            return null;
        }

        for (Pattern p : excludes) {
            if (p != null) {
                if (p.matcher(tableName).matches()) {
                    return null;
                }
            }
        }

        return include.columnsFilter;
    }



    protected boolean visitNode(EJBQLExpressionVisitor visitor) {
        return visitor.visitAbstractSchemaName(this);
    }



    /**
     * Returns a new ObjectContext instance based on the runtime's main
     * DataChannel.
     * 
     * @since 3.2
     */
    public ObjectContext newContext() {
        return injector.getInstance(ObjectContextFactory.class).createContext();
    }



    /**
     * Returns a new ObjectContext which is a child of the specified
     * DataChannel. This method is used for creation of nested ObjectContexts,
     * with parent ObjectContext passed as an argument.
     * 
     * @since 3.2
     */
    public ObjectContext newContext(DataChannel parentChannel) {
        return injector.getInstance(ObjectContextFactory.class).createContext(parentChannel);
    }



    /**
     * Visits this not without recursion. Default implementation simply returns true.
     * Subclasses override this method to call an appropriate visitor method.
     */
    protected boolean visitNode(EJBQLExpressionVisitor visitor) {
        return true;
    }



	/**
	 * Opens a DBRecord instance with the given multiple primary keys. In
	 * contrast of openRecord(DBRowSet, Object[]) this method returns null if no
	 * matching record exists.
	 *
	 * @param table
	 *            the table to read the record from
	 * @param keys
	 *            the primary keys array
	 * @return the DBRecord instance, can be null
	 */
	public DBRecord getRecord(final DBRowSet table, final Object[] keys) {

		class ReadRecordCallback implements ConnectionCallback<DBRecord> {
			public DBRecord doInConnection(Connection connection)
					throws SQLException, DataAccessException {
				DBRecord record = new EmpireRecord();
				try {
					record.read(table, keys, connection);
				} catch (RecordNotFoundException e) {
					return null;
				}
				return record;
			}
		}

		return getJdbcTemplate().execute(new ReadRecordCallback());

	}



    /**
     * navigates to the desired page. Depending on the page outcome provided this is either a forward or a redirect.
     * @param outcome the destination page to navigate to
     */
    protected void navigateTo(PageOutcome outcome)
    {
        if (log.isDebugEnabled())
            log.debug("Redirecting from page {} to page {}.", getPageName(), outcome.toString());
        // Return to Parent
        FacesContext context = FacesContext.getCurrentInstance();
        // Retrieve the NavigationHandler instance..
        NavigationHandler navHandler = context.getApplication().getNavigationHandler();
        // Invoke nav handling..
        navHandler.handleNavigation(context, action, outcome.toString());
        // Trigger a switch to Render Response if needed
        context.renderResponse();
    }



	private boolean isCompressed() {
		List<ContentCodingType> contentCodingTypes = this.getHeaders().getContentEncoding();
		for (ContentCodingType contentCodingType : contentCodingTypes) {
			if (contentCodingType.equals(ContentCodingType.GZIP)) {
				return true;
			}
		}
		return false;
	}



    
    public boolean hasType(RepositoryConnection connection, URI resource, URI type) throws RepositoryException {
        return connection.hasStatement(resource, RDF.TYPE, type, true, ldpContext);
    }



    @Reference
    public void setAlertsSources(AlertsSourcesService alertsSources) {
        this.alertsSources = alertsSources;
    }    



    public String createJavascriptReference(ComponentReference componentReference) throws IOException {
        Binding binding = componentReference.getBindings().get(0);
        URI targetURI = URI.create(binding.getURI());
        String targetPath = targetURI.getPath();
        
        return "AtomClient(\"" + targetPath + "\")";        
    }



    public String createJavascriptReference(ComponentReference componentReference) throws IOException {
        Binding binding = componentReference.getBindings().get(0);
        URI targetURI = URI.create(binding.getURI());
        String targetPath = targetURI.getPath();
        
        return "JSONRpcClient(\"" + targetPath + "\").Service";        
    }



    @Reference
    public void setAlerts(AlertsService alerts) {
        this.alerts = alerts;
    }    



    public static void debug() {
        if (DEBUG) {
            System.out.print(NEWLINE);
        }
    }



    public boolean getInstalled() {
        return installed;
    }



    
    public List<String> getConfigs(String groupName) throws Exception {
        // check if the group exists
        Group group = groupManager.findGroupByName(groupName);
        if (group == null) {
            throw new IllegalArgumentException("Cluster group " + groupName + " doesn't exist");
        }

        List<String> result = new ArrayList<String>();

        Map<String, Properties> clusterConfigurations = clusterManager.getMap(Constants.CONFIGURATION_MAP + Configurations.SEPARATOR + groupName);
        for (String pid : clusterConfigurations.keySet()) {
            result.add(pid);
        }

        return result;
    }



    
    public List<String> getUrls(String groupName) throws Exception {
        // check if the group exists
        Group group = groupManager.findGroupByName(groupName);
        if (group == null) {
            throw new IllegalArgumentException("Cluster group " + groupName + " doesn't exist");
        }

        List<String> result = new ArrayList<String>();
        Set<String> clusterUrls = clusterManager.getSet(Constants.URLS_DISTRIBUTED_SET_NAME + Configurations.SEPARATOR + groupName);
        for (String url : clusterUrls) {
            result.add(url);
        }
        return result;
    }



    private BadCredentialsException badCredentials() {
        return new BadCredentialsException(messages.getMessage(
                        "LdapAuthenticationProvider.badCredentials", "Bad credentials"));
    }



    /**
     * Provide a {@link MethodSecurityExpressionHandler} that is registered with
     * the {@link ExpressionBasedPreInvocationAdvice}. The default is
     * {@link DefaultMethodSecurityExpressionHandler} which optionally will
     * Autowire an {@link AuthenticationTrustResolver}.
     *
     * <p>
     * Subclasses may override this method to provide a custom
     * {@link MethodSecurityExpressionHandler}
     * </p>
     *
     * @return
     */
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodExpressionHandler;
    }



    /**
     * Used by the default implementation of {@link #authenticationManager()} to
     * attempt to obtain an {@link AuthenticationManager}. If overridden, the
     * {@link AuthenticationManagerBuilder} should be used to specify the
     * {@link AuthenticationManager}.
     *
     * <p>
     * The {@link #authenticationManagerBean()} method can be used to expose the
     * resulting {@link AuthenticationManager} as a Bean. The
     * {@link #userDetailsServiceBean()} can be used to expose the last
     * populated {@link UserDetailsService} that is created with the
     * {@link AuthenticationManagerBuilder} as a Bean. The
     * {@link UserDetailsService} will also automatically be populated on
     * {@link HttpSecurity#getSharedObject(Class)} for use with other
     * {@link SecurityContextConfigurer} (i.e. RememberMeConfigurer )
     * </p>
     *
     * <p>
     * For example, the following configuration could be used to register in
     * memory authentication that exposes an in memory
     * {@link UserDetailsService}:
     * </p>
     *
     * <pre>
     * &#064;Override
     * protected void configure(AuthenticationManagerBuilder auth) {
     *     auth
     *         // enable in memory based authentication with a user named
     *         // &quot;user&quot; and &quot;admin&quot;
     *         .inMemoryAuthentication()
     *             .withUser(&quot;user&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;).and()
     *             .withUser(&quot;admin&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;, &quot;ADMIN&quot;);
     * }
     *
     * // Expose the UserDetailsService as a Bean
     * &#064;Bean
     * &#064;Override
     * public UserDetailsService userDetailsServiceBean() throws Exception {
     *     return super.userDetailsServiceBean();
     * }
     *
     * </pre>
     *
     * @param auth
     *            the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        this.disableAuthenticationRegistration = true;
    }



    /**
     * Maps a {@link List} of {@link SimpDestinationMessageMatcher} instances.
     *
     * @param typesToMatch the {@link SimpMessageType} instance to match on
     * @return the {@link Constraint} associated to the matchers.
     */
    public Constraint simpTypeMatchers(SimpMessageType... typesToMatch) {
        MessageMatcher<?>[] typeMatchers = new MessageMatcher<?>[typesToMatch.length];
        for (int i = 0; i < typesToMatch.length; i++) {
            SimpMessageType typeToMatch = typesToMatch[i];
            typeMatchers[i] = new SimpMessageTypeMatcher(typeToMatch);
        }
        return matchers(typeMatchers);
    }



    /**
     * Maps a {@link List} of {@link SimpDestinationMessageMatcher} instances.
     * If no destination is found on the Message, then the Matcher returns
     * false.
     *
     * @param type the {@link SimpMessageType} to match on. If null, the {@link SimpMessageType} is not considered for matching.
     * @param patterns the patterns to create {@link org.springframework.security.messaging.util.matcher.SimpDestinationMessageMatcher}
     *                    from. Uses {@link MessageSecurityMetadataSourceRegistry#simpDestPathMatcher(PathMatcher)}.
     *
     * @return the {@link Constraint}  that is associated to the {@link MessageMatcher}
     * @see {@link MessageSecurityMetadataSourceRegistry#simpDestPathMatcher(PathMatcher)}
     */
    private Constraint simpDestMatchers(SimpMessageType type, String... patterns) {
        List<MatcherBuilder> matchers = new ArrayList<MatcherBuilder>(patterns.length);
        for(String pattern : patterns) {
            matchers.add(new PathMatcherMessageMatcherBuilder(pattern, type));
        }
        return new Constraint(matchers);
    }



    /**
     * Sub classes can override this method to register different types of authentication. If not overridden,
     * {@link #configure(AuthenticationManagerBuilder)} will attempt to autowire by type.
     *
     * @param auth the {@link AuthenticationManagerBuilder} used to register different authentication mechanisms for the
     *                 global method security.
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        this.disableAuthenticationRegistry = true;
    }



	private Mono<Authentication> flatMapAuthentication(ServerWebExchange exchange) {
		return exchange.getPrincipal()
			.cast(Authentication.class)
			.defaultIfEmpty(this.anonymousAuthenticationToken);
	}



    private void checkState() {
        if (parent == null && providers.isEmpty()) {
            throw new IllegalArgumentException("A parent AuthenticationManager or a list " +
                    "of AuthenticationProviders is required");
        }
    }



	
	protected Access registerMatcher(ServerWebExchangeMatcher matcher) {
		this.matcher = matcher;
		return new Access();
	}



  public boolean hasInitData(byte[] initData) {
    return Arrays.equals(this.initData, initData);
  }



  /**
   * Returns the number of output samples that can be read with {@link #getOutput(ShortBuffer)}.
   */
  public int getSamplesAvailable() {
    return numOutputSamples;
  }



  private static boolean getIsCompressed(UrlResponseInfo info) {
    for (Map.Entry<String, String> entry : info.getAllHeadersAsList()) {
      if (entry.getKey().equalsIgnoreCase("Content-Encoding")) {
        return !entry.getValue().equalsIgnoreCase("identity");
      }
    }
    return false;
  }



  private void releaseBuffers() {
    nextInputBuffer = null;
    nextSubtitleEventIndex = C.INDEX_UNSET;
    if (subtitle != null) {
      subtitle.release();
      subtitle = null;
    }
    if (nextSubtitle != null) {
      nextSubtitle.release();
      nextSubtitle = null;
    }
  }



    private static boolean builtIn(final String manager) {
        return manager == null || manager.isEmpty()
                    || "openejb".equalsIgnoreCase(manager)
                    || "tomee".equalsIgnoreCase(manager);
    }



    /**
     * Gets the value of the includeExistingValidators property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIncludeExistingValidators() {
        return includeExistingValidators;
    }



    private DataSource getTargetDataSource() {
        return getDelegate().getDataSource();
    }



    /**
     * Gets the value of the ignoreAnnotations property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIgnoreAnnotations() {
        return ignoreAnnotations;
    }



    public SecurityConstaintBuilder displayName(final String displayName) {
        securityConstraint.setDisplayName(displayName);
        return this;
    }



    private void runEndRequestTasks() {
        for (final Runnable r : endRequestRunnables.get()) {
            try {
                r.run();
            } catch (final Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        endRequestRunnables.remove();
    }



    private static boolean isQuit(String line) {
        if (QUIT_CMD.equalsIgnoreCase(line) || EXIT_CMD.equalsIgnoreCase(line)) {
            return true;
        }

        //http://youtrack.jetbrains.com/issue/IDEA-94826
        line = new StringBuilder(line).reverse().toString();

        return QUIT_CMD.equalsIgnoreCase(line) || EXIT_CMD.equalsIgnoreCase(line);
    }



    
    public void stop() {
        cleanTimerData();
        shutdownMyScheduler();
    }



    public boolean isEnableMtom() {
        return enableMtom != null && enableMtom;
    }



    /**
     * Gets the value of the ignoreAnnotations property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIgnoreAnnotations() {
        return ignoreAnnotations;
    }



    public static void exit(Object oldContext) {
        if (oldContext != null && !(oldContext instanceof WebBeansContext)) throw new IllegalArgumentException("ThreadSingletonServiceImpl can only be used with WebBeansContext, not " + oldContext.getClass().getName());
        contexts.set((WebBeansContext) oldContext);
    }



    /**
     * Returns the parent of this GPathResult. If this GPathResult has no parent the GPathResult itself is returned.
     * This is no navigation in the XML tree. It is backtracking on the GPath expression chain.
     * It is the bevavior of parent() prior to 2.2.0.
     * Backtracking on '..' actually goes down one level in the tree again.
     * find() and findAll() are popped along with the level they have been applied to.
     *
     * @return the parent or <code>this</code>
     */
    public GPathResult pop() {
        return this.parent;
    }



    /**
     * Search for the script file, doesn't bother if it is named precisely.
     *
     * Tries in this order:
     * - actual supplied name
     * - name.groovy
     * - name.gvy
     * - name.gy
     * - name.gsh
     *
     * @since 2.3.0
     */
    public static File searchForGroovyScriptFile(String input) {
        String scriptFileName = input.trim();
        File scriptFile = new File(scriptFileName);
        // TODO: Shouldn't these extensions be kept elsewhere?  What about CompilerConfiguration?
        // This method probably shouldn't be in GroovyMain either.
        String[] standardExtensions = {".groovy",".gvy",".gy",".gsh"};
        int i = 0;
        while (i < standardExtensions.length && !scriptFile.exists()) {
            scriptFile = new File(scriptFileName + standardExtensions[i]);
            i++;
        }
        // if we still haven't found the file, point back to the originally specified filename
        if (!scriptFile.exists()) {
            scriptFile = new File(scriptFileName);
        }
        return scriptFile;
    }



    private boolean notEnableFastPath(StatementMeta meta) {
        // return false if cannot do fast path and if are already on the path
        return fastPathBlocked || meta==null || !meta.optimize || controller.isFastPath();
    }



    /**
     * Removes the last item from the List. Using add() and pop()
     * is similar to push and pop on a Stack.
     * <pre class="groovyTestCase">
     * def list = ["a", false, 2]
     * assert list.removeLast() == 2
     * assert list == ["a", false]
     * </pre>
     *
     * @param self a List
     * @return the item removed from the List
     * @throws NoSuchElementException if the list is empty and you try to pop() it.
     * @since 2.5.0
     */
    public static <T> T removeLast(List<T> self) {
        if (self.isEmpty()) {
            throw new NoSuchElementException("Cannot pop() an empty List");
        }
        return self.remove(self.size() - 1);
    }



    public void addComponentsForRow() {
        rowIndex = parent.nextRowIndex();

        // iterate through the rows and add each one to the layout...
        for (Iterator iter = cells.iterator(); iter.hasNext(); ) {
            TableLayoutCell cell = (TableLayoutCell) iter.next();
            GridBagConstraints c = cell.getConstraints();
            c.gridy = rowIndex;
            // add the cell to the table
            parent.addCell(cell);
        }        
    }



    public static boolean isCase(Collection caseValue, Object switchValue) {
        return caseValue.contains(switchValue);
    }



    protected Number unaryMinusImpl(Number left) {
        return toBigInteger(left).negate();
    }



    protected String getFirstChildText(AST node) {
        AST child = node.getFirstChild();
        return child != null ? child.getText() : null;
    }



    protected Number unaryMinusImpl(Number left) {
        return toBigDecimal(left).negate();
    }



    /**
     * Tries to set a property on null, which will always fail
     *
     * @param property - the proprty to set
     * @param newValue - the new value of the property
     */
    public void setProperty(String property, Object newValue) {
        throw new NullPointerException("Cannot set property '" + property + "' on null object");
    }



    /**
     * Tries to get a property on null, which will always fail
     *
     * @param property - the property to get
     * @return a NPE
     */
    public Object getProperty(String property) {
        throw new NullPointerException("Cannot get property '" + property + "' on null object");
    }



    private static String xgetAt(NamedNodeMap namedNodeMap, String elementName) {
        Attr a = (Attr) namedNodeMap.getNamedItem(elementName);
        return a.getValue();
    }



    private static Object xgetAt(Element element, String elementName) {
        if ("..".equals(elementName)) {
            return parent(element);
        }
        if ("**".equals(elementName)) {
            return depthFirst(element);
        }
        if (elementName.startsWith("@")) {
            return element.getAttribute(elementName.substring(1));
        }
        return getChildElements(element, elementName);
    }



    protected Number unaryMinusImpl(Number left) {
        return new Double(-left.doubleValue());
    }



    public void update() {
        fireBinding();
    }



   /**
     * Returns secure property as Boolean.
     * @return boolean
     */
    private boolean isSecure() {
    	if(this.secureBoolean != null){
    		return this.secureBoolean.booleanValue();
    	}
    	else{
    		return false;
    	}
    }  



   
   public RenderURL createRedirectURL(Copy option) throws IllegalStateException {
      if (stateChanged) {
         throw new IllegalStateException("sendRedirect no longer allowed after navigational state changes");
      }
      return new RenderURLImpl(responseContext, option);
   }



    /**
     * Replicates all current known repositories.
     */
    
    public void execute() {
        // Take a snapshot of the current available replicators...
        Map<ServiceReference<RepositoryReplication>, RepositoryReplication> replicators = new HashMap<>(m_replicators);

        // The URL to the server to replicate...
        URL master = m_discovery.discover();

        for (Entry<ServiceReference<RepositoryReplication>, RepositoryReplication> entry : replicators.entrySet()) {
            ServiceReference<RepositoryReplication> ref = entry.getKey();
            RepositoryReplication repository = entry.getValue();

            try {
                replicate(master, ref, repository);
            }
            catch (Exception e) {
                m_log.log(LogService.LOG_WARNING, "Replicating repository '" + ref.getProperty("name") + "' failed!", e);
            }
        }
    }



    private static Set<String> split(String value) {
        Set<String> trimmedValues = new HashSet<String>();
        if (value != null) {
            String[] rawValues = value.split(",");
            for (String rawValue : rawValues) {
                trimmedValues.add(rawValue.trim());
            }
        }
        return trimmedValues;
    }



    private Map<RepositoryType, RepositoryProvider> createProviderMap( )
    {
        Map<RepositoryType, RepositoryProvider> map = new HashMap<>( );
        if ( repositoryProviders != null )
        {
            for ( RepositoryProvider provider : repositoryProviders )
            {
                for ( RepositoryType type : provider.provides( ) )
                {
                    map.put( type, provider );
                }
            }
        }
        return map;
    }



    public RepositoryGroup cronExpression( String mergedIndexCronExpression )
    {
        this.cronExpression = mergedIndexCronExpression;
        return this;
    }



  /**
   * This method will check whether default value is present in the given filter values
   */
  private void ifDefaultValueMatchesFilter() {
    if (!this.isDimensionPresentInCurrentBlock[0]) {
      CarbonDimension dimension = this.dimColEvaluatorInfoList.get(0).getDimension();
      byte[] defaultValue = dimension.getDefaultValue();
      if (null != defaultValue) {
        for (int k = 0; k < filterRangeValues.length; k++) {
          int maxCompare =
              ByteUtil.UnsafeComparer.INSTANCE.compareTo(filterRangeValues[k], defaultValue);
          if (maxCompare <= 0) {
            isDefaultValuePresentInFilter = true;
            break;
          }
        }
      }
    }
  }



  private BatchResult getBatchResult() {
    BatchResult batchResult = new BatchResult();
    synchronized (lock) {
      updateDataBlockIterator();
      if (dataBlockIterator != null) {
        batchResult.setRows(dataBlockIterator.next());
      }
    }
    return batchResult;
  }



  /**
   * This method will check whether default value is present in the given filter values
   */
  private void ifDefaultValueMatchesFilter() {
    if (!this.isDimensionPresentInCurrentBlock[0]) {
      CarbonDimension dimension = this.dimColEvaluatorInfoList.get(0).getDimension();
      byte[] defaultValue = dimension.getDefaultValue();
      if (null != defaultValue) {
        for (int k = 0; k < filterRangeValues.length; k++) {
          int maxCompare =
              ByteUtil.UnsafeComparer.INSTANCE.compareTo(filterRangeValues[k], defaultValue);
          if (maxCompare >= 0) {
            isDefaultValuePresentInFilter = true;
            break;
          }
        }
      }
    }
  }



  /**
   * This method will check whether default value is present in the given filter values
   */
  private void ifDefaultValueMatchesFilter() {
    if (!this.isDimensionPresentInCurrentBlock[0]) {
      CarbonDimension dimension = this.dimColEvaluatorInfoList.get(0).getDimension();
      byte[] defaultValue = dimension.getDefaultValue();
      if (null != defaultValue) {
        for (int k = 0; k < filterRangeValues.length; k++) {
          int maxCompare =
              ByteUtil.UnsafeComparer.INSTANCE.compareTo(filterRangeValues[k], defaultValue);
          if (maxCompare > 0) {
            isDefaultValuePresentInFilter = true;
            break;
          }
        }
      }
    }
  }



  /**
   * This method will check whether default value is present in the given filter values
   */
  private void ifDefaultValueMatchesFilter() {
    if (!this.isDimensionPresentInCurrentBlock[0]) {
      CarbonDimension dimension = this.dimColEvaluatorInfoList.get(0).getDimension();
      byte[] defaultValue = dimension.getDefaultValue();
      if (null != defaultValue) {
        for (int k = 0; k < filterRangeValues.length; k++) {
          int maxCompare =
              ByteUtil.UnsafeComparer.INSTANCE.compareTo(filterRangeValues[k], defaultValue);
          if (maxCompare < 0) {
            isDefaultValuePresentInFilter = true;
            break;
          }
        }
      }
    }
  }



    private static int calculateHashCode(byte a[], int offset, int length) {
        if (a == null)
            return 0;
        int result = 1;
        for (int i = offset; i < offset + length; i++) {
            result = 31 * result + a[i];
        }
        return result;
    }



    @AfterClass
    public static void cleanUpAfterTestSuite() throws Exception {
        TestPhoenixIndexRpcSchedulerFactory.reset();
    }



    private static void adjustQualifierRange(Integer qualifier, Pair<Integer, Integer> minMaxQualifiers) {
        if (minMaxQualifiers.getFirst() == null) {
            minMaxQualifiers.setFirst(qualifier);
            minMaxQualifiers.setSecond(qualifier);
        } else {
            if (minMaxQualifiers.getFirst() > qualifier) {
                minMaxQualifiers.setFirst(qualifier);
            } else if (minMaxQualifiers.getSecond() < qualifier) {
                minMaxQualifiers.setSecond(qualifier);
            }
        }
    }



    public boolean projectEveryRow() {
        return isProjectEmptyKeyValue;
    }



    
    public boolean hasIndexWALCodec() {
        return true;
    }



	
	public EnvironmentClasspathConfigurer siteYarnAppClasspath(String... defaultClasspath) {
		this.defaultYarnAppClasspath = StringUtils.arrayToCommaDelimitedString(defaultClasspath);
		return this;
	}



	
	public List<JobInstance> findJobInstancesByJobName(String jobName, int start, int count) {
		List<JobInstance> jobInstances = new ArrayList<JobInstance>();
		try {
			FindJobInstancesByJobNameReq request = JobRepositoryRpcFactory.buildFindJobInstancesByJobNameReq(jobName, start, count);
			FindJobInstancesByJobNameRes response = (FindJobInstancesByJobNameRes) getAppmasterScOperations().doMindRequest(request);
			for (JobInstanceType j : response.jobInstances) {
				jobInstances.add(JobRepositoryRpcFactory.convertJobInstanceType(j));
			}
		} catch (Exception e) {
			throw convertException(e);
		}
		return jobInstances;
	}



	@Bean
	public ObjectPostProcessor<Object> yarnObjectPostProcessor(AutowireCapableBeanFactory beanFactory) {
		return new AutowireBeanFactoryObjectPostProcessor(beanFactory);
	}



	public String executeAndfetchOne(final String command) {
		List<String> results = execute(command);
		if (results.size() < 1) {
			throw new IncorrectResultSizeDataAccessException(1);
		}
		return results.get(0);
	}



	
	public void next() {
		counter++;
	}



  public void process( final JCas jCas ) throws AnalysisEngineProcessException {
    //this.dataWriter.write(new Instance<String>("#DEBUG " + ViewUriUtil.getURI(docCas)));
    LOGGER.info( "Finding Coreferences ..." );

    // It is possible that the cas for an entire patient has been passed through.  Try to process all documents.
    final Collection<JCas> docViews = PatientViewUtil.getDocumentViews( jCas );
    if ( docViews.isEmpty() ) {
      // There is only one document in the cas - the default
      processDocument( jCas );
      LOGGER.info( "Finished." );
      return;
    }
    try ( DotLogger dotter = new DotLogger() ) {
      for ( JCas view : docViews ) {
        processDocument( view );
      }
    } catch ( IOException ioE ) {
      LOGGER.error( ioE.getMessage() );
    }
    LOGGER.info( "Finished." );
  }



   /**
    *
    * @return html to end body
    */
   static private String endBody() {
      return "</body>\n" +
             "</html>\n";
   }



    /**
     * @return {@code true} if Elliptic Curve Cryptography is supported
     * @see #ECC_SUPPORTED_PROP
     */
    public static boolean isECCSupported() {
        if (hasEcc == null) {
            String propValue = System.getProperty(ECC_SUPPORTED_PROP);
            if (GenericUtils.isEmpty(propValue)) {
                try {
                    getKeyPairGenerator(KeyUtils.EC_ALGORITHM);
                    hasEcc = Boolean.TRUE;
                } catch (Throwable t) {
                    hasEcc = Boolean.FALSE;
                }
            } else {
                Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
                logger.info("Override ECC support value: " + propValue);
                hasEcc = Boolean.valueOf(propValue);
            }
        }

        return hasEcc;
    }



    
    public void writing(ServerSession session, String remoteHandle, FileHandle localHandle,
                      long offset, byte[] data, int dataOffset, int dataLen)
                              throws IOException {
        if (log.isTraceEnabled()) {
            log.trace("write(" + session + ")[" + localHandle.getFile() + "] offset=" + offset + ", requested=" + dataLen);
        }
    }



    
    default T apply(byte[] input) {
        return parse(input);
    }



    private void resetToHTMLStream()
    {
        // m_htmlcharInfo remains unchanged
        // m_htmlInfo = null;  // Don't reset
        m_inBlockElem = false;
        m_inDTD = false;
        m_omitMetaTag = false;
        m_specialEscapeURLs = true;     
    }



    protected void couldThrowException() throws Exception
    {
        return; // don't do anything.
    }



  /**
   * Return our current Active state
   */
  public boolean isEnabled()
  {
    return m_IsActive;
  }



	/**
   * This will execute the following XSLT instructions
   * from the snapshot point.
   */
  public void resetToStylesheet(TransformSnapshot ts)
		//throws TransformerException
  {
    ((TransformSnapshotImpl)ts).apply(this);		
  }



    /**
     * Set the first attribute of this element
     */
    public void setFirstAttribute(SyntaxTreeNode attribute) {
	if (_attributeElements == null) {
	    _attributeElements = new Vector(2);
	}
	_attributeElements.insertElementAt(attribute,0);
    }



  /**
   * Cast result object to a nodelist. Always issues an error.
   *
   * @return null
   *
   * @throws javax.xml.transform.TransformerException
   */
  public DTMIterator iter() throws javax.xml.transform.TransformerException
  {

    error(XPATHErrorResources.ER_CANT_CONVERT_TO_NODELIST,
          new Object[]{ getTypeString() });  //"Can not convert "+getTypeString()+" to a NodeList!");

    return null;
  }



  /**
   * For functions to override.
   *
   * @return null
   */
  public int rtf()
  {
    return DTM.NULL;
  }



  /**
   * Cast result object to a result tree fragment.
   *
   * @return The document fragment this wraps
   */
  public int rtf()
  {
    return m_dtmRoot;
  }



    /**
     * The index of the translet pointer within the execution of
     * the test method.
     */
    public Instruction loadTranslet() {
	return _aloadTranslet;
    }



  /**
   * Cast result object to a result tree fragment.
   *
   * @param support XPath context to use for the conversion
   *
   * @return The object as a result tree fragment.
   */
  public int rtf(XPathContext support)
  {
    // DTM frag = support.createDocumentFragment();
    // %REVIEW%
    return DTM.NULL;
  }



  /**
   * Cast result object to a result tree fragment.
   *
   * @param support Xpath context to use for the conversion
   *
   * @return A document fragment with this string as a child node
   */
  public int rtf(XPathContext support)
  {

    DTM frag = support.createDocumentFragment();

    frag.appendTextChild(str());

    return frag.getDocument();
  }



    /**
     * The index of the translet pointer within the execution of
     * matchFrom or matchCount.
     * Overridden from ClassGenerator.
     */
    public Instruction loadTranslet() {
	return _aloadTranslet;
    }



    
    public Boolean getBootable() {
        return null;
    }



    private static synchronized void initInitializeBeans(boolean initializeBeans) {
        s_initializeBeans = initializeBeans;
    }



    
    public boolean isIpAllocatedToVpc(IpAddress ip) {
        return (ip != null && ip.getVpcId() != null && 
                (ip.isOneToOneNat() || !_firewallDao.listByIp(ip.getId()).isEmpty()));
    }



    protected StorageSubsystemCommandHandler buildStorageHandler() {
        final XenServerStorageProcessor processor = new XenServerStorageProcessor(this);
        return new StorageSubsystemCommandHandlerBase(processor);
    }



    
    public boolean getSystemUse() {
        return systemUse;
    }



    
    public boolean hasActiveVMSnapshotTasks(Long vmId){
        List<VMSnapshotVO> activeVMSnapshots = _vmSnapshotDao.listByInstanceId(vmId, 
                VMSnapshot.State.Creating, VMSnapshot.State.Expunging,VMSnapshot.State.Reverting,VMSnapshot.State.Allocated);
        return activeVMSnapshots.size() > 0;
    }



    public boolean canAttemptFencing() {
        return fenceFuture == null || fenceFuture.isDone();
    }



    
    public List<NetworkOfferingServiceMapVO> listByNetworkOfferingId(long networkOfferingId) {
        SearchCriteria<NetworkOfferingServiceMapVO> sc = AllFieldsSearch.create();
        sc.setParameters("networkOfferingId", networkOfferingId);
        return listBy(sc);
    }



    
    public boolean canProviderSupportServiceInNetwork(long networkId, Service service, Provider provider) {
        SearchCriteria<NetworkServiceMapVO> sc = AllFieldsSearch.create();
        sc.setParameters("networkId", networkId);
        sc.setParameters("service", service.getName());
        sc.setParameters("provider", provider.getName());
        if (findOneBy(sc) != null) {
            return true;
        } else {
            return false;
        }
    }



    public void saveDetails(List<R> details) {
        if (details.isEmpty()) {
            return;
        }
        TransactionLegacy txn = TransactionLegacy.currentTxn();
        txn.start();
        SearchCriteria<R> sc = AllFieldsSearch.create();
        sc.setParameters("resourceId", details.get(0).getResourceId());
        expunge(sc);
        
        for (R detail : details) {
            persist(detail);
        }
        
        txn.commit();
    }



    public Map<String, String> listDetailsKeyPairs(long resourceId) {
        SearchCriteria<R> sc = AllFieldsSearch.create();
        sc.setParameters("resourceId", resourceId);
        
        List<R> results = search(sc, null);
        Map<String, String> details = new HashMap<String, String>(results.size());
        for (R result : results) {
            details.put(result.getName(), result.getValue());
        }
        return details;
    }



    public List<R> listDetails(long resourceId) {
        SearchCriteria<R> sc = AllFieldsSearch.create();
        sc.setParameters("resourceId", resourceId);

        List<R> results = search(sc, null);
        return results;
    }



    public String ip4() {
        return _addr;
    }



    public Boolean getPasswordEnabled() {
        return passwordEnabled;
    }



    public Boolean getBootable() {
        return bootable;
    }



    
    public boolean unassignAsa1000v(String tenantName, String firewallDn) throws ExecutionException {
        String xml = VnmcXml.UNASSIGN_ASA1000V.getXml();
        String service = VnmcXml.UNASSIGN_ASA1000V.getService();
        xml = replaceXmlValue(xml, "cookie", _cookie);
        xml = replaceXmlValue(xml, "binddn", getDnForEdgeFirewall(tenantName) + "/binding");
        xml = replaceXmlValue(xml, "fwdn", firewallDn);

        String response =  sendRequest(service, xml);
        return verifySuccess(response);
    }



    
    public boolean assignAsa1000v(String tenantName, String firewallDn) throws ExecutionException {
        String xml = VnmcXml.ASSIGN_ASA1000V.getXml();
        String service = VnmcXml.ASSIGN_ASA1000V.getService();
        xml = replaceXmlValue(xml, "cookie", _cookie);
        xml = replaceXmlValue(xml, "binddn", getDnForEdgeFirewall(tenantName) + "/binding");
        xml = replaceXmlValue(xml, "fwdn", firewallDn);

        String response =  sendRequest(service, xml);
        return verifySuccess(response);
    }



    
    public boolean shutdown(Network network, ReservationContext context, boolean cleanup) throws ConcurrentOperationException, ResourceUnavailableException {
        // TODO kill all loadbalancer vms by calling the ElasticLoadBalancerManager
        return false;
    }



    
    public Boolean getPasswordEnabled() {
        return null;
    }



    
    public void showDashboardActivity() {

        startActivity(new Intent(getActivity(), DashboardMainActivity.class));
        getActivity().finish();
    }



    void doSetName(String name) {
        this.name = name;
    }



    
    public Object execute() throws Exception {
        JaasRealm realm = (JaasRealm) session.get(JAAS_REALM);
        AppConfigurationEntry entry = (AppConfigurationEntry) session.get(JAAS_ENTRY);

        if (realm == null || entry == null) {
            System.err.println("No JAAS Realm/Login Module has been selected");
            return null;
        }

        BackingEngine engine = getBackingEngine(entry);

        if (engine == null) {
            System.err.println("Can't get the list of users (no backing engine service found)");
            return null;
        }

        return doExecute(engine);
    }



    
    public Object execute() throws Exception {
        ShellTable table = new ShellTable();

        table.column("JMS Topics");

        for (String topic : getJmsService().topics(connectionFactory, username, password)) {
            table.addRow().addContent(topic);
        }

        table.print(System.out);

        return null;
    }



    
    public Object execute() throws Exception {
        getJmsService().send(connectionFactory, queue, message, replyTo, username, password);
        return null;
    }



    /**
     * Features matching the given feature selectors including dependent features
     * 
     * @param features feature selector name, name/version, name/version-range
     * 
     * @return matching features 
     */
    public Set<Feature> getMatching(List<String> features) {
        Set<Feature> selected = new HashSet<>();
        for (String feature : features) {
            addFeatures(feature, selected, true);
        }
        return selected;
    }



    
    public Object execute() throws Exception {
        System.out.println(getJmsService().move(connectionFactory, source, destination, selector, username, password) + " message(s) moved");
        return null;
    }



    
    public Object execute() throws Exception {
        //Cleanup the session
        session.put(JAAS_REALM, null);
        session.put(JAAS_ENTRY, null);
        session.put(JAAS_CMDS, new LinkedList<JaasCommandSupport>());
        return null;
    }



    public void deactivateComponent(String componentName) throws Exception {
        final Component component = findComponent(componentName);
        if(component != null)
            component.disable();
        else
            LOGGER.warn("No component found for name: " + componentName);
    }



    
    public Object execute() throws Exception {
        getJmsService().delete(name);
        return null;
    }



    
    public Object execute() throws Exception {
        ShellTable table = new ShellTable();

        table.column("JMS Queues");

        for (String queue : getJmsService().queues(connectionFactory, username, password)) {
            table.addRow().addContent(queue);
        }

        table.print(System.out);

        return null;
    }



    
    public Object execute() throws Exception {
        webContainerService.start(ids);
        return null;
    }



    
    public Object execute() throws Exception {
        ShellTable table = new ShellTable();
        table.column("Messages Count");
        table.addRow().addContent(getJmsService().count(connectionFactory, queue, username, password));
        table.print(System.out);
        return null;
    }



    
    public Object execute() throws Exception {
        JaasRealm realm = (JaasRealm) session.get(JAAS_REALM);
        AppConfigurationEntry entry = (AppConfigurationEntry) session.get(JAAS_ENTRY);

        if (realm == null || entry == null) {
            System.err.println("No JAAS Realm/Login Module selected");
            return null;
        }

        BackingEngine engine = getBackingEngine(entry);

        if (engine == null) {
            System.err.println("Can't update the JAAS realm (no backing engine service registered)");
            return null;
        }

        return doExecute(engine);
    }



    
    public Object execute() throws Exception {
        doExecute(repositoryAdmin);
        return null;
    }



    
    public Object execute() throws Exception {

        ShellTable table = new ShellTable();
        table.column("JMS Connection Factory");

        List<String> connectionFactories = getJmsService().connectionFactories();
        for (String connectionFactory : connectionFactories) {
            table.addRow().addContent(connectionFactory);
        }

        table.print(System.out);

        return null;
    }



    
    public Object execute() throws Exception {
        this.getJdbcService().execute(datasource, command);
        return null;
    }



    
    public Object execute() throws Exception {
        webContainerService.stop(ids);
        return null;
    }



    
    public Object execute() throws Exception {
        findResource();
        return null;
    }



    
    public Object execute() throws Exception {
        System.out.println("Executing My Command Demo");
        return null;
    }



    
    public Object execute() throws Exception {
        System.out.println(getJmsService().consume(connectionFactory, queue, selector, username, password) + " message(s) consumed");
        return null;
    }



    
    public Object execute() throws Exception {
        logService.log(toLevel(level.toUpperCase()), message);
        return null;
    }



    
    public Object execute() throws Exception {
    	if (onlyDuplicates) {
    		checkDuplicateExports();
    	} else {
    		showExports();
    	}
        return null;
    }



    
    public Object execute() throws Exception {
        System.out.printf(format, arguments.toArray());
        return null;
    }



    
    public Object execute() throws Exception {
        ShellTable table = new ShellTable();
        table.column("Property");
        table.column("Value");

        Map<String, String> info = getJmsService().info(connectionFactory, username, password);
        for (String key : info.keySet()) {
            table.addRow().addContent(key, info.get(key));
        }

        table.print(System.out);

        return null;
    }



    public Class<?> primaryType()
    {
        return types.stream().findFirst().orElse( null );
    }



    /**
     * return the next vlaue as a byte
     */
    public byte readByte() {
        return BYTE_SERIALIZER.fromByteBuffer( getNext() );
    }



    /**
     * Return the value as a byte array
     */
    public byte[] readBytes() {
        return BYTES_ARRAY_SERIALIZER.fromByteBuffer( getNext() );
    }



    /**
     * Return the value as an integer
     */
    public int readInteger() {
        return INTEGER_SERIALIZER.fromByteBuffer( getNext() );
    }



    public void redirectToMainView(){
        setContent( mainView );
    }



    
    public String simpleInfo() {
        return tag().typeStr() + " ["
            + "tag=" + tag()
            + ", off=" + getOffset()
            + ", len=" + getHeaderLength() + "+" + getBodyLength()
            + "]";
    }



    private void initGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(EncryptionKey.class, new EncryptionKeyAdapter());
        gsonBuilder.registerTypeAdapter(PrincipalName.class, new PrincipalNameAdapter());
        gsonBuilder.registerTypeAdapter(KerberosTime.class, new KerberosTimeAdapter());
        gsonBuilder.enableComplexMapKeySerialization();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }



    public void clearCache() { cache.clear(); }



    /**
     * Invoked when the Registry is shutdown; sets the shutdown flag and releases the object and the creator.
     */
    public void run()
    {
        creator = new ObjectCreator<T>()
        {
            public T createObject()
            {
                throw new IllegalStateException(ServiceMessages.registryShutdown(serviceId));
            }
        };

        object = null;
    }



    List<T> toMutableList()
    {
        List<T> result = new ArrayList<T>(count);

        for (int i = 0; i < count; i++)
        {
            result.add(values[start + i]);
        }

        return result;
    }



    public void run()
    {
        registry.clearCache();
        cache.clear();
    }



    /**
     * @return test parameter (if negate is false), or test parameter inverted (if negate is true)
     */
    protected boolean test()
    {
        return test != negate;
    }



    public void run()
    {
        changeTracker.clear();
        classToInstantiator.clear();
        proxyFactory.clearCache();

        // Release the existing class pool, loader and so forth.
        // Create a new one.

        initializeService();
    }



    /**
     * Clears the registry on an invalidation event (this is because the registry caches results, and the keys are
     * classes that may be component classes from the invalidated component class loader).
     */
    public void run()
    {
        registry.clearCache();
    }



    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    @Contribute(RequestHandler.class)
    public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration,
     @Local
     RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }



    Object doUpdateSelected(String nodeId, boolean selected)
    {
        TreeNode node = model.getById(nodeId);

        String event;

        if (selected)
        {
            selectionModel.select(node);

            event = EventConstants.NODE_SELECTED;
        } else
        {
            selectionModel.unselect(node);

            event = EventConstants.NODE_UNSELECTED;
        }

        CaptureResultCallback<Object> callback = CaptureResultCallback.create();

        resources.triggerEvent(event, new Object[]{nodeId}, callback);

        final Object result = callback.getResult();

        if (result != null)
        {
            return result;
        }

        return new JSONObject();
    }



    Object doExpandChildren(String nodeId)
    {
        TreeNode container = model.getById(nodeId);

        expansionModel.markExpanded(container);

        return new RenderNodes(container.getChildren());
    }



    Object doMarkExpanded(@RequestParameter(NODE_ID) String nodeId)
    {
        expansionModel.markExpanded(model.getById(nodeId));

        return new JSONObject();
    }



    Object doMarkCollapsed(@RequestParameter(NODE_ID) String nodeId)
    {
        expansionModel.markCollapsed(model.getById(nodeId));

        return new JSONObject();
    }



    /**
     * Creates an infinite lazy flow from an initial value and a function to map from the current value to the
     * next value.
     * 
     * @param <T>
     * @param initial
     *            initial value in flow
     * @param function
     *            maps from current value in flow to next value in flow
     * @return lazy flow
     */
    public static <T> Flow<T> iterate(final T initial, final Mapper<T, T> function)
    {
        LazyFunction<T> head = new LazyFunction<T>()
        {
            public LazyContinuation<T> next()
            {
                return new LazyContinuation<T>(initial, toLazyFunction(initial, function));
            }
        };

        return lazy(head);
    }



    public <T> void addLoggingAdvice(Logger logger, MethodAdviceReceiver receiver)
    {
        MethodAdvice advice = new LoggingAdvice(logger, exceptionTracker);

        receiver.adviseAllMethods(advice);
    }



    private <T> T getServiceByTypeAlone(Class<T> serviceInterface)
    {
        List<String> serviceIds = findServiceIdsForInterface(serviceInterface);

        if (serviceIds == null)
            serviceIds = Collections.emptyList();

        switch (serviceIds.size())
        {
            case 0:

                throw new RuntimeException(IOCMessages.noServiceMatchesType(serviceInterface));

            case 1:

                String serviceId = serviceIds.get(0);

                return getService(serviceId, serviceInterface);

            default:

                Collections.sort(serviceIds);

                throw new RuntimeException(IOCMessages.manyServiceMatches(serviceInterface, serviceIds));
        }
    }



    /**
     * Invoked by InvalidationEventHub
     */
    
    public void run()
    {
        registry.clearCache();
    }



    public Method getMethod()
    {
        return bundle.getMethod(getInstance());
    }



    /**
     * @return test parameter inverted
     */
    protected boolean test()
    {
        return !test;
    }



    /**
     * Use preProcess to add any references and properties dynamically
     * TODO: also support introspection and handle WEB-INF/web.componentType (spec line 503)
     */
    public void build(Component component) {
        if (!(component instanceof RuntimeComponent)) {
            return;
        }
        RuntimeComponent rtc = (RuntimeComponent) component;

        for (Reference reference : rtc.getReferences()) {
            if (getReference(reference.getName()) == null) {
                getReferences().add(createReference(reference));
            }
        }

        for (Property property : rtc.getProperties()) {
            if (getProperty(property.getName()) == null) {
                getProperties().add(createProperty(property));
            }
        }
    }



    public void stop() {
        if (jsr237WorkManager instanceof ThreadPoolWorkManager) {
            // Allow privileged access to modify threads. Requires RuntimePermission in security
            // policy.
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    ((ThreadPoolWorkManager)jsr237WorkManager).destroy();
                    return null;
                }
            });
        }
    }



    private DocumentBuilder createDocumentBuilder() {
        try {
            return documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new IllegalArgumentException(e);
        }
    }



    private Transformer createTransformer() {
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new IllegalArgumentException(e);
        }
        return transformer;
    }



    public void stop() {
        for (Registry registry : rmiRegistries.values()) {
            try {
                UnicastRemoteObject.unexportObject(registry, false);
            } catch (NoSuchObjectException e) {
                e.printStackTrace();
            }
        }
        rmiRegistries.clear();
    }



    /**
     *
     * @param writer
     * @param uri
     * @throws XMLStreamException
     */
    private String setPrefix(XMLStreamWriter writer, String uri) throws XMLStreamException {
        if (uri == null) {
            return null;
        }
        String prefix = writer.getPrefix(uri);
        if (prefix != null) {
            return null;
        } else {

            // Find an available prefix and bind it to the given URI
            NamespaceContext nsc = writer.getNamespaceContext();
            for (int i=1; ; i++) {
                prefix = "ns" + i;
                if (nsc.getNamespaceURI(prefix) == null) {
                    break;
                }
            }
            writer.setPrefix(prefix, uri);
            return prefix;
        }

    }



  /**
   * Shuts down all plugins
   * 
   * @throws PluginRuntimeException
   */
  private void shutDownActivatedPlugins() throws PluginRuntimeException {
    for (Plugin plugin : fActivatedPlugins.values()) {
      plugin.shutDown();
    }
  }



  /**
   * Aborting driver without stopping tasks.
   *
   * @return driver status
   */
  public Status abortDriver() {
    this.driverLock.lock();
    try {
      if (isRunning()) {
        LOGGER.info("Aborting driver...");
        this.driverStatus = this.driver.abort();
        LOGGER.info("Aborted driver with status: {}", this.driverStatus);
      }
    } finally {
      this.driverLock.unlock();
    }
    return driverStatus;
  }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayM() {
        return PATTERN_M.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] stringGetBytes() {
        return STR.getBytes();
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayMD() {
        return PATTERN_M_D.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArraySpace() {
        return PATTERN_SPACE.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayMEx() {
        return PATTERN_M_EX.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayMDEx() {
        return PATTERN_M_D_EX.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayMC() {
        return PATTERN_M_C.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayMCDEx() {
        return PATTERN_M_C_D_EX.toByteArray(EVENT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] byteArrayMCD() {
        return PATTERN_M_C_D.toByteArray(EVENT);
    }



    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long systemCurrentTimeMillis() {
        return System.currentTimeMillis();
    }



    
    public MimeMessage build() {
        return message;
    }



    @GenerateMicroBenchmark
    public Message test10_getMessageUsingReflection(final RandomInteger rng)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Constructor<? extends Message> constructor = StringFormattedMessage.class.getConstructor(String.class,
            Object[].class);
        return constructor.newInstance("Hello %i", new Object[]{rng.random});
    }



    @GenerateMicroBenchmark
    public String test01_getCallerClassNameFromStackTrace() {
        return new Throwable().getStackTrace()[3].getClassName();
    }



    @GenerateMicroBenchmark
    public Message test09_getMessageUsingNew(final RandomInteger rng) {
        return new StringFormattedMessage("Hello %i", rng.random);
    }



    @GenerateMicroBenchmark
    public Class<?> test05_getStackTraceClassForClassName() throws ClassNotFoundException {
        return Class.forName(new Throwable().getStackTrace()[3].getClassName());
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] defaultStringGetBytesCharSet() {
        return LOGMSG.getBytes(CHARSET_DEFAULT);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] defaultStringGetBytes() {
        return LOGMSG.getBytes();
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] defaultStringGetBytesString() throws Exception {
        return LOGMSG.getBytes(DEFAULT_ENCODING);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] shiftJisStringGetBytesString() throws Exception {
        return LOGMSG.getBytes(STRING_SHIFT_JIS);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] usAsciiStringGetBytesCharSet() {
        return LOGMSG.getBytes(CHARSET_US_ASCII);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] usAsciiStringGetBytesString() throws Exception {
        return LOGMSG.getBytes(STRING_US_ASCII);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] iso8859_1StringGetBytesString() throws Exception {
        return LOGMSG.getBytes(STRING_ISO8859_1);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] iso8859_1StringGetBytesCharSet() {
        return LOGMSG.getBytes(CHARSET_ISO8859_1);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] shiftJisStringGetBytesCharSet() {
        return LOGMSG.getBytes(CHARSET_SHIFT_JIS);
    }



    /**
     * Creates a builder for a custom PatternLayout.
     * @return a PatternLayout builder.
     */
    @PluginBuilderFactory
    public static Builder newBuilder() {
        return new Builder();
    }



    
    public void terminate() {
        stop();
    }



    
    public Map<String, String> toMap() {
        return getCopy();
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String fixedBitFiddlingReuseCharArray(final BufferState state) {
        final int len = formatCharArrayBitFiddling(System.currentTimeMillis(), state.charArray, 0);
        return new String(state.charArray, 0, len);
    }



    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String fixedFormatReuseStringBuilder(final BufferState state) {
        state.stringBuilder.setLength(0);
        formatStringBuilder(System.currentTimeMillis(), state.stringBuilder);
        return new String(state.stringBuilder);
    }



    final boolean isReadOnly() {
        return (flags & READONLY) != 0;
    }



    final void isReadOnly(boolean value) {
        flags = (short) (value ? flags | READONLY : flags & ~READONLY);
    }



    /**
     * [schema specified] 
     * @see <a href="http://www.w3.org/TR/xmlschema-1/#e-schema_specified">XML Schema Part 1: Structures [schema specified]</a>
     * @return false value was specified in schema, true value comes from the infoset
     */
    public boolean getIsSchemaSpecified() {
        return fSpecified;
    }



    /**
     * Convenience method. Check if <code>exclusion</code> is a substitution
     * group exclusion for this element declaration.
     * @param exclusion Extension, restriction or none. Represents final
     *   set for the element.
     * @return True if <code>exclusion</code> is a part of the substitution
     *   group exclusion subset.
     */
    public boolean isSubstitutionGroupExclusion(short exclusion) {
        return (fFinal & exclusion) != 0;
    }



    /**
     * Convenience method. Check if <code>disallowed</code> is a disallowed
     * substitution for this element declaration.
     * @param disallowed Substitution, extension, restriction or none.
     *   Represents a block set for the element.
     * @return True if <code>disallowed</code> is a part of the substitution
     *   group exclusion subset.
     */
    public boolean isDisallowedSubstitution(short disallowed) {
        return (fBlock & disallowed) != 0;
    }



    /**
     * {final} For complex type definition it is a subset of {extension,
     * restriction}. For simple type definition it is a subset of
     * {extension, list, restriction, union}.
     * @param derivation  Extension, restriction, list, union constants
     *   (defined in <code>XSConstants</code>).
     * @return True if derivation is in the final set, otherwise false.
     */
    public boolean isFinal(short derivation) {
        return (fFinal & derivation) != 0;
    }



    /**
     * {prohibited substitutions} A subset of {extension, restriction}.
     * @param prohibited  extention or restriction constants (defined in
     *   <code>XSConstants</code>).
     * @return True if prohibited is a prohibited substitution, otherwise
     *   false.
     */
    public boolean isProhibitedSubstitution(short prohibited) {
        return (fBlock & prohibited) != 0;
    }



    final void isReadOnly(boolean value) {
        flags = (short) (value ? flags | READONLY : flags & ~READONLY);
    }



    final boolean isOwned() {
        return (flags & OWNED) != 0;
    }



    final boolean isReadOnly() {
        return (flags & READONLY) != 0;
    }



    final void isIgnorableWhitespace(boolean value) {
        flags = (short) (value ? flags | IGNORABLEWS : flags & ~IGNORABLEWS);
    }



    final boolean needsSyncData() {
        return (flags & SYNCDATA) != 0;
    }



    final void isOwned(boolean value) {
        flags = (short) (value ? flags | OWNED : flags & ~OWNED);
    }



    final void needsSyncData(boolean value) {
        flags = (short) (value ? flags | SYNCDATA : flags & ~SYNCDATA);
    }



    final void needsSyncChildren(boolean value) {
        flags = (short) (value ? flags | SYNCCHILDREN : flags & ~SYNCCHILDREN);
    }



    final void isFirstChild(boolean value) {
        flags = (short) (value ? flags | FIRSTCHILD : flags & ~FIRSTCHILD);
    }



    final boolean isSpecified() {
        return (flags & SPECIFIED) != 0;
    }



    final void isSpecified(boolean value) {
        flags = (short) (value ? flags | SPECIFIED : flags & ~SPECIFIED);
    }



    final boolean isFirstChild() {
        return (flags & FIRSTCHILD) != 0;
    }



    final boolean needsSyncChildren() {
        return (flags & SYNCCHILDREN) != 0;
    }



    /**
     * Introduced in DOM Level 2. <p>
     * Tests whether the DOM implementation implements a specific feature and that
     * feature is supported by this node.
     * @param feature       The package name of the feature to test. This is the
     *                      same name as what can be passed to the method
     *                      hasFeature on DOMImplementation.
     * @param version       This is the version number of the package name to
     *                      test. In Level 2, version 1, this is the string "2.0". If
     *                      the version is not specified, supporting any version of
     *                      the feature will cause the method to return true.
     * @return boolean      Returns true if this node defines a subtree within which the
     *                      specified feature is supported, false otherwise.
     * @since WD-DOM-Level-2-19990923
     */
    public boolean isSupported(String feature, String version)
    {
        return ownerDocument().getImplementation().hasFeature(feature,
                                                              version);
    }



    final boolean internalIsIgnorableWhitespace() {
        return (flags & IGNORABLEWS) != 0;
    }



    /**
     * [schema specified] 
     * @see <a href="http://www.w3.org/TR/xmlschema-1/#e-schema_specified">XML Schema Part 1: Structures [schema specified]</a>
     * @return false value was specified in schema, true value comes from the infoset
     */
    public boolean getIsSchemaSpecified() {
        return fSpecified;
    }



    /** DOM Level 3: isId*/
    public boolean isId(){
        // REVISIT: should an attribute that is not in the tree return
        // isID true?
        return isIdAttribute();
    }



    /**
     * Remove all objects from the Augmentations structure.
     */
    public void removeAllItems() {
        fAugmentationsContainer.clear();
    }



    /**
     * @since DOM Level 3 
     */
    public boolean isId(){
        return false;
    }



    public boolean isFinal(short derivation) {
        return (fFinalSet & derivation) != 0;
    }



    /**
     * Returns the <code>index</code>th item in the collection. The index
     * starts at 0. If <code>index</code> is greater than or equal to the
     * number of nodes in the list, this returns <code>null</code>.
     * @param index index into the collection.
     * @return The XSObject at the <code>index</code>th position in the
     *   <code>XSObjectList</code>, or <code>null</code> if that is not a
     *   valid index.
     */
    public XSObject item(int index) {
        if (index < 0 || index >= fLength)
            return null;
        return fArray[index];
    }



	private void calcFundamentalFacets() {
		setOrdered();
		setNumeric();
		setBounded();
		setCardinality();
	}



  /**
   * Log a client error, and throw an exception.
   *
   * @param str     The message to use in the log and the exception.
   */
  static void throwClientError(String str) {
    LOG.error(str);
    throw new RuntimeException(str);
  }



    /**
     * Checks whether this record has a comment, false otherwise.
     *
     * @return true if this record has a comment, false otherwise
     * @since 1.3
     */
    public boolean hasComment() {
        return comment != null;
    }



    private final char mapNullToDisabled(Character c) {
        return c == null ? DISABLED : c.charValue();
    }



    /**
     * Returns the last character that was read as an integer (0 to 65535). This will be the last character returned by
     * any of the read methods. This will not include a character read using the {@link #peek()} method. If no
     * character has been read then this will return {@link #UNDEFINED}. If the end of the stream was reached on the
     * last read then this will return {@link #END_OF_STREAM}.
     *
     * @return the last character that was read
     */
    int getLastChar() {
        return lastChar;
    }



    /**
     * Specifies whether spaces around values are ignored when parsing input.
     *
     * @return {@code true} if spaces around values are ignored, {@code false} if they are treated as part of the
     *         value.
     */
    public boolean getIgnoreSurroundingSpaces() {
        return ignoreSurroundingSpaces;
    }



    /**
     * Specifies whether missing column names are allowed when parsing the header line.
     *
     * @return {@code true} if missing column names are allowed when parsing the header line, {@code false} to throw an
     *         {@link IllegalArgumentException}.
     */
    public boolean getAllowMissingColumnNames() {
        return allowMissingColumnNames;
    }



    /**
     * Specifies whether empty lines between records are ignored when parsing input.
     *
     * @return {@code true} if empty lines between records are ignored, {@code false} if they are turned into empty
     *         records.
     */
    public boolean getIgnoreEmptyLines() {
        return ignoreEmptyLines;
    }



    /**
     * Returns whether to skip the header record.
     *
     * @return whether to skip the header record.
     */
    public boolean getSkipHeaderRecord() {
        return skipHeaderRecord;
    }



    /**
     * Write an octal integer into a buffer.
     *
     * Uses {@link #formatUnsignedOctalString} to format
     * the value as an octal string with leading zeros.
     * The converted number is followed by space and NUL
     * 
     * @param value The value to write
     * @param buf The buffer to receive the output
     * @param offset The starting offset into the buffer
     * @param length The size of the output buffer
     * @return The updated offset, i.e offset+length
     * @throws IllegalArgumentException if the value (and trailer) will not fit in the buffer
     */
    public static int formatOctalBytes(final long value, byte[] buf, final int offset, final int length) {

        int idx=length-2; // For space and trailing null
        formatUnsignedOctalString(value, buf, offset, idx);

        buf[offset + idx++] = (byte) ' '; // Trailing space
        buf[offset + idx]   = 0; // Trailing null

        return offset + length;
    }



    /**
     * Writes an octal value into a buffer.
     * 
     * Uses {@link #formatUnsignedOctalString} to format
     * the value as an octal string with leading zeros.
     * The converted number is followed by NUL and then space.
     *
     * @param value The value to convert
     * @param buf The destination buffer
     * @param offset The starting offset into the buffer.
     * @param length The size of the buffer.
     * @return The updated value of offset, i.e. offset+length
     * @throws IllegalArgumentException if the value (and trailer) will not fit in the buffer
     */
    public static int formatCheckSumOctalBytes(final long value, byte[] buf, final int offset, final int length) {

        int idx=length-2; // for NUL and space
        formatUnsignedOctalString(value, buf, offset, idx);

        buf[offset + idx++]   = 0; // Trailing null
        buf[offset + idx]     = (byte) ' '; // Trailing space

        return offset + length;
    }



    /**
     * Set this entry's file size.
     *
     * @param size This entry's new file size.
     * @throws IllegalArgumentException if the size is &lt; 0.
     */
    public void setSize(long size) {
        if (size < 0){
            throw new IllegalArgumentException("Size is out of range: "+size);
        }
        this.size = size;
    }



    /**
     * Provide the wrapped WebContext for this composite.
     * @return The wrapped WebContext
     */
    protected WebContext webContext() {
        return (WebContext) this.getBaseContext();
    }



    /**
     * <p>
     * Provide the ServletWebContext for this composite.
     * </p>
     * @return Our ServletWebContext
     */
    protected ServletWebContext servletWebContext() {
        return (ServletWebContext) this.getBaseContext();
    }



    /**
     *  Add a session id cookie if appropriate. Can be overloaded to
     *  support a cluster.
     * @param conn
     * @param urlString
     * @param request
     * @ since Struts 1.2.0
     */
    protected void addCookie(URLConnection conn, String urlString, HttpServletRequest request) {
        if ((conn instanceof HttpURLConnection)
            && urlString.startsWith(request.getContextPath())
            && (request.getRequestedSessionId() != null)
            && request.isRequestedSessionIdFromCookie()) {
            StringBuffer sb = new StringBuffer("JSESSIONID=");
            sb.append(request.getRequestedSessionId());
            conn.setRequestProperty("Cookie", sb.toString());
        }
    }



    /**
     * Constructs the beginning &lt;script&gt; element depending on XHTML 
     * status.
     * @since Struts 1.2
     */
    protected String renderStartElement() {
        StringBuffer start = new StringBuffer("<script type=\"text/javascript\"");

        // there is no language attribute in XHTML
        if (!this.isXhtml() && this.scriptLanguage) {
            start.append(" language=\"Javascript1.1\"");
        }

        if (this.src != null) {
            start.append(" src=\"" + src + "\"");
        }

        start.append("> \n");
        return start.toString();
    }



    /**
     * Check file write permission.
     */
    public boolean isWritable() {
        LOG.debug("Checking authorization for " + getAbsolutePath());
        if (user.authorize(new WriteRequest(getAbsolutePath())) == null) {
            LOG.debug("Not authorized");
            return false;
        }

        LOG.debug("Checking if file exists");
        if (file.exists()) {
            LOG.debug("Checking can write: " + file.canWrite());
            return file.canWrite();
        }
        
        LOG.debug("Authorized");
        return true;
    }



    /**
     * Check read permission.
     */
    public boolean isReadable() {
        return file.canRead();
    }



    public boolean isKeyboardFolderTraversalEnabled() {
        return keyboardFolderTraversalEnabled;
    }



    public int getCount() {
        return hashMap.getCount();
    }



    public int getCount() {
        return set.size();
    }



    public int getCount() {
        return count;
    }



    public int getCount() {
        return map.size();
    }



    public boolean isVariableItemHeight() {
        return variableItemHeight;
    }



	public Connection<?> getConnection(ConnectionKey connectionKey) {
		try {
			return jdbcTemplate.queryForObject(selectFromUserConnection() + " where userId = ? and providerId = ? and providerUserId = ?", connectionMapper, userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchConnectionException(connectionKey);
		}
	}



	/**
	 * Decorates a request factory to buffer responses so that the responses may be repeatedly read.
	 * @param requestFactory the request factory to be decorated for buffering
	 * @return a buffering request factory
	 */
	public static ClientHttpRequestFactory bufferRequests(ClientHttpRequestFactory requestFactory) {
		return new BufferingClientHttpRequestFactory(requestFactory);
	}
